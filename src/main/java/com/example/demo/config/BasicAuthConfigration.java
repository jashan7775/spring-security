package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import com.example.demo.serviceImpl.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
// basic auth configuration
public class BasicAuthConfigration{
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private PasswordEncoderConfig passwordEncoderConfig;
	
	// by default spring security provides form based authentication 
	// below code is to configure form based authentication
	
	// there is one more alternative to define role - go to method where we want to and write @PreAuthorize("hasRole('ADMIN')") above that method
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	//.csrf().disable() // if our client is not browser then we had to disable this
        	.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // if we dont disable csrf then we can manually get token and pass to post request
        	.and()
            .authorizeRequests()
            .requestMatchers("/signin").permitAll()
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
            //.httpBasic()
            
            // FORM BASED AUTHENTICATION :
            .formLogin() // if we want form based authentication
            .loginPage("/signin") // if we want to change url from login to signin (if we do this we need to construct the signin page)
            .loginProcessingUrl("/dologin") // tells what action had to perform on hiting url (dologin this defines in login.html page)
            .defaultSuccessUrl("/api/get-all-users") // tells on which page we fall after log in
            ;
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
	   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	   }
	   
	   @Autowired
	   public void configure(AuthenticationManagerBuilder auth) throws Exception{
		   auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoderConfig.passwordEncoder());
	   }
}
