package com.example.demo.serviceImpl;

import java.util.Collection;
import java.util.HashSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.entity.UserEntity;

// spring security puts user details in UserDetail interface we had to implement that interface
// in this interface information of user is stored
public class CustomUserDetail implements UserDetails {

	private UserEntity user;

	public CustomUserDetail(UserEntity user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<SimpleGrantedAuthority> role = new HashSet<>();
		role.add(new SimpleGrantedAuthority(this.user.getRole()));
		return role;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUserName();
	}

}
