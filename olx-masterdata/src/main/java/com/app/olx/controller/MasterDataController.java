package com.app.olx.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.olx.model.AdvertiseCategory;
import com.app.olx.model.StatusList;
import com.app.olx.service.MasterDataService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/zensar")
public class MasterDataController {
	
	@Autowired
	private MasterDataService masterDataService;
	
	@GetMapping(value = "/advertise/category", 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Get All Advertise Category.")
	public List<AdvertiseCategory> getAllAdvertiseCategories(@RequestHeader("auth-token") String authToken){
		return masterDataService.getAllAdvertiseCategories(authToken);
	}
	
	@GetMapping(value = "/advertise/status", 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Get All Possible Advertise Status")
	public List<StatusList> getAllPossibleAdvertiseStatus(@RequestHeader("auth-token") String authToken) {
		return masterDataService.getAllPossibleAdvertiseStatus(authToken);
	}
	
	
}
