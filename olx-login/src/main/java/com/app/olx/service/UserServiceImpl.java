package com.app.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.olx.entity.UserEntity;
import com.app.olx.exception.InvalidUserException;
import com.app.olx.exception.UnAuthenticatedException;
import com.app.olx.model.Login;
import com.app.olx.model.User;
import com.app.olx.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public User login(Login login) {
		
		Optional<UserEntity> userEntity = userRepo.findByUserNameAndPassword(login.getUserName(), login.getPassword());
		if(userEntity.isPresent()) {
			UserEntity user = userEntity.get();
			User userNew = new User(user.getId(),
					user.getFirstName(), 
					user.getLastName(), 
					user.getUserName(), 
					user.getPassword(), 
					user.getEmail(), 
					user.getPhone());
			return userNew;
		}
		throw new UnAuthenticatedException("Invalid Username or password : "+login.getUserName()+"/"+login.getPassword());
	}

	@Override
	public boolean logout(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User registerUser(User user) {
		UserEntity userEntity = new UserEntity(user.getFirstName(), user.getLastName(), user.getUserName(),
				user.getPassword(), user.getEmail(), user.getPhone());
		UserEntity newUser = userRepo.save(userEntity);
		return new User(newUser.getId(), newUser.getFirstName(), newUser.getLastName(), newUser.getUserName(),
				newUser.getPassword(), newUser.getEmail(), newUser.getPhone());
	}

	@Override
	public User getUserInfo(Integer id, String token) {
		Optional<UserEntity> user = userRepo.findById(id);
		if(user.isPresent()) {
			UserEntity newUser = user.get();
			return new User(newUser.getId(), newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(),
					newUser.getPassword(), newUser.getEmail(), newUser.getPhone());
		}
		throw new InvalidUserException("user id = "+id);
	}

	@Override
	public List<User> getAllUsers(String token) {
		List<UserEntity> usersList = userRepo.findAll();
		List<User> users = new ArrayList<User>();
		
		usersList.stream().forEach(s -> 
		users.add(new User(s.getId(), 
				s.getFirstName(), 
				s.getLastName(), 
				s.getUserName(), 
				s.getPassword(), 
				s.getEmail(), 
				s.getPhone())));
		return users;
	}

}
