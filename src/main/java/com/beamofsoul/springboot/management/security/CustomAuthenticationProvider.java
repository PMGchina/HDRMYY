package com.beamofsoul.springboot.management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.beamofsoul.springboot.entity.dto.UserExtension;
import com.beamofsoul.springboot.other.entity.Patient;
import com.beamofsoul.springboot.other.service.PatientService;
import com.beamofsoul.springboot.service.UserService;

/**
 * @ClassName CustomAuthenticationProvider
 * @Description 自定义身份验证提供器
 * @author MingshuJian
 * @Date 2017年1月19日 下午4:18:21
 * @version 1.0.0
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private UserService userService;
	
	/*
	 * (非 Javadoc)  
	 * <p>Title: authenticate</p>  
	 * <p>Description: 根据用户名读取用户身份信息，并提供简单的密码匹配验证</p>  
	 * @param authentication
	 * @return UsernamePasswordAuthenticationToken
	 * @throws AuthenticationException  
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//数据库中User对象实例
		UserDetails user = customUserDetailsService.loadUserByUsername(authentication.getName());
		
		//此处可以增加加密验证或验证码验证等功能
		//先判断是否是remember-me登录
		if (authentication.getPrincipal() instanceof UserExtension) {
			UserExtension userExtension = (UserExtension) authentication.getPrincipal();
			if (!userExtension.getPassword().equals(user.getPassword())) {
				Patient patient = patientService.findByMedicalCard(userExtension.getUsername());
				if (patient.getPassword().equals(userExtension.getPassword())) {
					userService.changePassword(userExtension.getUserId(), user.getPassword(), userExtension.getPassword());
				} else {
					throw new UsernameNotFoundException("密码错误");
				}
			}
		} 
		//不是remember-me登录，直接检查输入的密码与从数据库中获取的User对象的密码是否一致
		else if (!authentication.getCredentials().equals(user.getPassword())) {
			UserExtension userExtension = (UserExtension) user;
			//密码不一致，先从前置数据库中获取对应的患者信息
			Patient patient = patientService.findByMedicalCard(authentication.getPrincipal().toString());
			//如果患者的医保卡密码在前置数据库中更新了，则将User表中的相应密码更改为最新密码，登录成功
			if (patient != null && patient.getPassword().equals(authentication.getCredentials())) {
				userService.changePassword(userExtension.getUserId(), userExtension.getPassword(), patient.getPassword());
			} else {
				throw new UsernameNotFoundException("密码错误");
			}
		}

		UsernamePasswordAuthenticationToken result = 
				new UsernamePasswordAuthenticationToken(
				user.getUsername(), 
				user.getPassword(),
				user.getAuthorities());
		result.setDetails(user);
		return result;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
