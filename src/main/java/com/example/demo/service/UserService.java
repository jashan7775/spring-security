package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.UserEntity;

public interface UserService {

	public List<UserEntity> getAllUsers();

	public List<UserEntity> getUserByName(String userName);

}
