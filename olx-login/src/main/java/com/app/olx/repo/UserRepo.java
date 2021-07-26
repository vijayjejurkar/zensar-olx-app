package com.app.olx.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.olx.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer>{
	
	Optional<UserEntity> findByUserNameAndPassword(String userName, String password);

}