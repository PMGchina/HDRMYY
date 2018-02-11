package com.beamofsoul.springboot.entity.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserExtension extends User {

	private static final long serialVersionUID = -8269621072024835804L;

	private Long userId;
	private String nickname;
	private String sex;
	private String brthDate;

	public UserExtension(Long userId, String username, String password, String nickname, String sex, String brthDate,
			boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userId = userId;
		this.nickname = nickname;
	}
}
