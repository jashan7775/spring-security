package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	List<UserEntity> userList = new ArrayList<>();

	public UserServiceImpl() {
		UserEntity one = new UserEntity("jashan", "jashan@2001", "jashan@gmail.com");
		UserEntity two = new UserEntity("harsh", "harsh@2004", "harsh@gmail.com");
		userList.add(one);
		userList.add(two);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userList;
	}

	@Override
	public List<UserEntity> getUserByName(String userName) {
		List<UserEntity> filteredList = new ArrayList<>();
		for (UserEntity overUserList : userList) {
			if (overUserList.getUserName().equals(userName)) {
				filteredList.add(overUserList);
			}
		}
		return filteredList;
	}

	@Override
	public UserEntity createUser(UserEntity entity) {
		UserEntity newUser = new UserEntity(entity.getUserName(), entity.getPassword(), entity.getEmail());
		userList.add(newUser);
		return newUser;
	}

	@Override
	public UserEntity createNewUser(UserEntity entity) {
		String encryptedPassword = passwordEncoder.encode(entity.getPassword());
		entity.setPassword(encryptedPassword);
		UserEntity creatingUser = userRepo.save(entity);
		return creatingUser;
	}

}
