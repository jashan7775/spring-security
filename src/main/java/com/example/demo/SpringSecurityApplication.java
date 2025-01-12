package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.UserEntity;
import com.example.demo.repo.UserRepo;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserEntity userOne = new UserEntity();
		userOne.setUserName("jashan");
		userOne.setPassword(passwordEncoder.encode("jashan@2001"));
		userOne.setEmail("jashan@gmail.com");
		userOne.setRole("ADMIN");
		userRepo.save(userOne);
		
		UserEntity userTwo = new UserEntity();
		userTwo.setUserName("harsh");
		userTwo.setPassword(passwordEncoder.encode("harsh@2004"));
		userTwo.setEmail("harsh@gmail.com");
		userTwo.setRole("USER");
		userRepo.save(userTwo);

	}

}
