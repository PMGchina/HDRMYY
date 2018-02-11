package com.beamofsoul.springboot.management.security;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.beamofsoul.springboot.entity.Role;
import com.beamofsoul.springboot.entity.User;
import com.beamofsoul.springboot.entity.UserRole;
import com.beamofsoul.springboot.management.util.CodeGenerator;
import com.beamofsoul.springboot.other.entity.Patient;
import com.beamofsoul.springboot.other.service.PatientService;
import com.beamofsoul.springboot.service.RoleService;
import com.beamofsoul.springboot.service.UserRoleService;
import com.beamofsoul.springboot.service.UserService;

/**
 * @ClassName CustomUserDetailsService
 * @Description 自定义用户身份验证服务类
 * @author MingshuJian
 * @Date 2017年1月19日 下午4:28:32
 * @version 1.0.0
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;

	/*
	 * (非 Javadoc)
	 * <p>Title: loadUserByUsername</p>
	 * <p>Description: 获取用户信息后封装成UserExtension对象</p>
	 * @param username
	 * @return UserExtension
	 * @throws UsernameNotFoundException
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = getUser(username);
		return convertToUserExtension(user);
	}

	protected static com.beamofsoul.springboot.entity.dto.UserExtension convertToUserExtension(User user) {
		com.beamofsoul.springboot.entity.dto.UserExtension userExtension = new com.beamofsoul.springboot.entity.dto.UserExtension(
				user.getId(), user.getUsername(), user.getPassword(), user.getNickname(), user.getSex(), user.getBrthDate(),
				true,//是否可用
				true,//是否过期
				true,//证书不过期为true
				true,//账户未锁定为true
				getAuthorities(user));
		return userExtension;
	}

	/**
	 * @Title: getUser
	 * @Description: 通过用户名判断对象是否存在并返回
	 * @param @param username
	 * @param @return 参数
	 * @return User 返回类型
	 * @throws
	 */
	private User getUser(String username) {
		if (StringUtils.isBlank(username)) {
			throw new UsernameNotFoundException("用户名为空");
		}
		User user = getUser0(username);
		if (user == null) {
			//判断是否该用户为患者
			Patient patient = patientService.findByMedicalCard(username);
			if (patient == null) {
				throw new UsernameNotFoundException("用户不存在");
			} else {
				user = convertPatientToUser(patient);
			}
		}
		if (user.getStatus() == 0) {
			throw new UsernameNotFoundException("用户已被锁定");
		}
		if (user.getRoles() == null || user.getRoles().size() == 0) {
			throw new UsernameNotFoundException("用户暂未被分配角色");
		}
		return user;
	}

	/**
	 * @Title: convertPatientToUser
	 * @Description: 将新患者创建为新用户
	 * @param patient 患者对象
	 * @return User 创建好的用户对象
	 */
	private User convertPatientToUser(Patient patient) {
		User newUser = null;
		try {
			//创建新用户
			newUser = new User();
			newUser.setUsername(patient.getMedicalCard());
			newUser.setPassword(patient.getPassword());
			newUser.setNickname(patient.getName());
			newUser.setCode(CodeGenerator.generateInvitationCode());
			newUser.setStatus(patient.getAvailable() ? User.Status.NORMAL.getValue() : User.Status.LOCKED.getValue());
			userService.create(newUser);
			//给新用户分配角色
			UserRole userRole = new UserRole();
			userRole.setUser(newUser);
			userRole.setRole(roleService.findByDefaultRoleName());
			userRoleService.create(userRole);
			//给新用户实例绑定角色
			HashSet<Role> roles = new HashSet<Role>(1);
			roles.add(userRole.getRole());
			newUser.setRoles(roles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newUser;
	}

	protected User getUser0(String username) {
		return userService.findByUsername(username);
	}

	protected static Set<GrantedAuthority> getAuthorities(User user) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(
					"ROLE_" + role.getName().toUpperCase()));
		}
		return authorities;
	}

}
