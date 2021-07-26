package com.app.olx.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.olx.entity.UserEntityAuth;


public interface UserRepoAuth extends JpaRepository<UserEntityAuth, Integer>{

	List<UserEntityAuth> findByUsername(String username);

}