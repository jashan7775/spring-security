package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
// basic auth configuration
public class BasicAuthConfigration{
	
	// by default spring security provides form based authentication 
	// below code is to configure form based authentication
	
	// there is one more alternative to define role - go to method where we want to and write @PreAuthorize("hasRole('ADMIN')") above that method
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            
            // .requestMatchers("/public/**") // -> url started with /public will not be asked for username and password
            .requestMatchers("/public/home")
                        
            // role - high level view
            // permission - like read, write, delete..
            .hasRole("ADMIN")
            
            //.permitAll() // -> grant access to all users
            
            .requestMatchers("/api/**").hasRole("USER")
            
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
        return http.build();
    }
	
	// by default spring security provides us a default userName : user and password : (prints in console)
	// if we want in memory allocation use below code
	 @Bean
	 public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
	        // Define users for in-memory authentication
	        UserDetails user1 = User.builder()
	                .username("jashan")
	                .password(passwordEncoder.encode("jashan@2001"))
	                .roles("ADMIN")
	                .build();

	        UserDetails admin = User.builder()
	                .username("harsh")
	                .password(passwordEncoder.encode("harsh@2004"))
	                .roles("USER")
	                .build();

	        return new InMemoryUserDetailsManager(user1, admin);
	   }

	   @Bean
	   public PasswordEncoder passwordEncoder() {
		   // here we pass the strength by default it is ten
		    int srength = 12;
	        return new BCryptPasswordEncoder(srength); // Use BCrypt for encoding passwords
	   }

	   @Bean
	   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	   }
}
