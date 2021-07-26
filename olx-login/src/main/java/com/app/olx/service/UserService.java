package com.app.olx.service;

import java.util.List;

import com.app.olx.model.Login;
import com.app.olx.model.User;

public interface UserService {
	
	User login(Login login);
	boolean logout(String token);
	User registerUser(User user);
	User getUserInfo(Integer id, String token);
	List<User> getAllUsers(String token);

}
