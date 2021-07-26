package com.app.olx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class UserServiceDeletegateCircuitBreakerImpl implements UserServiceDelegate{

	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<Map> findByUsernames(String usernames) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public boolean isLoggedInUser(String authToken) {
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("AUTH_TOKEN_VALIDATION");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authToken);
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<Boolean> result = circuitBreaker.run(
					()-> this.restTemplate.exchange("http://auth-service/token/validate",  HttpMethod.GET, entity, Boolean.class),
					throwable-> fallbackForIsLoggedInUser(authToken, throwable));
		return result.getBody();
	}*/
	
	
	@Override
	@CircuitBreaker(name = "AUTH_TOKEN_VALIDATION", fallbackMethod = "fallbackForIsLoggedInUser")
	public boolean isLoggedInUser(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authToken);
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<Boolean> result = this.restTemplate.exchange("http://auth-service/token/validate", 
				HttpMethod.GET, entity, Boolean.class);
		return result.getBody();
	}
	
	public ResponseEntity<Boolean> fallbackForIsLoggedInUser(String authToken, Throwable throwable){
		System.out.println("Login service failed = "+throwable);
		return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
