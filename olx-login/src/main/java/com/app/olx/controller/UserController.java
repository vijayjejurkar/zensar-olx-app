package com.app.olx.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.olx.model.Login;
import com.app.olx.service.UserDetailServiceImpl;
import com.app.olx.service.UserService;
import com.app.olx.dto.*;
import com.app.olx.util.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername()
					, authenticationRequest.getPassword()));
		}catch(BadCredentialsException exception) {
			throw new BadCredentialsException(authenticationRequest.getUsername());
		}
		String jwtToken = jwtUtils.generateToken(authenticationRequest.getUsername());
		return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
	}
	
	@GetMapping(value = "/token/validate")
	public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String jwtToken) {
		String jwtActual = jwtToken.split(" ")[1];
		String username  = jwtUtils.extractUsername(jwtActual);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		boolean isValidToken = jwtUtils.validateToken(jwtActual, userDetails);
		if(isValidToken) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/*@PostMapping(value = "/user", 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Register User")
	public com.app.olx.model.User registerUser(@RequestBody com.app.olx.model.User user) {
		return userService.registerUser(user);
	}
	
	@GetMapping(value = "/user/{id}", 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Get User Information By Id.")
	public com.app.olx.model.User getUsersInfo(@ApiParam(value = "User Id", required = true) @PathVariable Integer id, @RequestHeader("auth-token") String token) {
		return userService.getUserInfo(id, token);
	}
	
	
	@GetMapping(value = "/user", 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Get All Users Information")
	public List<com.app.olx.model.User> getAllUsers(@RequestHeader("auth-token") String token){
		return userService.getAllUsers(token);
	}
	
	@PostMapping(value = "/user/authenticate", 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Login To Application Get Token")
	public com.app.olx.model.User login(@RequestBody Login login) {
		return userService.login(login);
		
	}
	
	@DeleteMapping(value = "/user/logout")
	@ApiOperation(value = "Logout from application")
	public boolean logout(@RequestHeader("auth-token") String token) {
		return userService.logout(token);
		
	}*/
}



















