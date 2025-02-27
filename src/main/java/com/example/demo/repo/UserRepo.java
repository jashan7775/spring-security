package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

	UserEntity findByUserName(String username);

}
