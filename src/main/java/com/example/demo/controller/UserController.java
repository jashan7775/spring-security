package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;;

	@GetMapping("/get-all-users")
//	@PreAuthorize("hasRole('ADMIN')")
	public List<UserEntity> getAllUsers() {
		List<UserEntity> dataFetched = userService.getAllUsers();
		return dataFetched;
	}

	@GetMapping("/get-user-by-name")
	public List<UserEntity> getUserByName(@RequestParam String userName) {
		List<UserEntity> dataFetched = userService.getUserByName(userName);
		return dataFetched;
	}

}
