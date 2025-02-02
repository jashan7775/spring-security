package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

	@Bean
	   public PasswordEncoder passwordEncoder() {
		   // here we pass the strength by default it is ten
		    int srength = 12;
	        return new BCryptPasswordEncoder(srength); // Use BCrypt for encoding passwords
	   }
	
}
