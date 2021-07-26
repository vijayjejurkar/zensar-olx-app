package com.app.olx.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.olx.model.Advertise;
import com.app.olx.model.Status;
import com.app.olx.service.AdvertiseService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/zensar")
public class AdvertiseController {
	
	@Autowired
	private AdvertiseService advertiseService;
	
	@PostMapping(value = "/advertise", 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Create Advertise")
	public Advertise createAdvertise(@RequestBody Advertise advertise, @RequestHeader("auth-token") String token) {
		return advertiseService.createAdvertise(advertise, token);	
	}
	
	@PutMapping(value = "/advertise/{id}", 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Update Advertise")
	public Advertise updateAdvertise(@RequestParam Integer id, @RequestBody Advertise advertise, @RequestHeader("auth-token") String token) {
		return advertiseService.updateAdvertise(id, advertise, token);	
	}
	
	
	@GetMapping(value = "/user/advertise",
				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Get All Advertised By Logged In User.")
	public List<Advertise> getAllAdvertisePostedByLoggedInUser(@RequestHeader("auth-token") String token){
		return advertiseService.getAllAdvertisePostedByLoggedInUser(token);
	}
	
	
	@GetMapping(value = "/user/advertise/{postId}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Get Add Advertise By Logged In User By Post ID")
	public Advertise getAllAdvertisePostedByLoggedInUser(@PathVariable Integer postId, @RequestHeader("auth-token") String token){
		return advertiseService.getAllAdvertisePostedByLoggedInUser(postId, token);
	}
	
	@GetMapping(value = "/advertise/search", 
				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Find Advertise By Search Text")
	public List<Advertise> findAdvertiseBySearchText(@RequestParam("search-text") String searchText, @RequestHeader("auth-token") String token){
		return advertiseService.findAdvertiseBySearchText(searchText, token);
	}
	
	@DeleteMapping(value = "/user/advertise/{postId}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Delete Advertise By Logged In User By Post ID")
	public boolean deleteByLoggedInUser(@PathVariable Integer postId, @RequestHeader("auth-token") String token){
		return advertiseService.deleteAdvertisePostedByLoggedInUser(postId, token);
	}
}
