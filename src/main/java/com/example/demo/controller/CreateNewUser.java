package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/create-new-user")
public class CreateNewUser {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserEntity> createNewUser(@RequestBody UserEntity entity) {
		try {
			UserEntity userCreated = userService.createNewUser(entity);
			return ResponseEntity.status(HttpStatus.OK).body(userCreated);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
