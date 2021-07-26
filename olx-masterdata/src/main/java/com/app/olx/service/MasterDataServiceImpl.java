package com.app.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.olx.entity.CategoryEntity;
import com.app.olx.entity.StatusListEntity;
import com.app.olx.exception.InvalidAuthorizationTokenException;
import com.app.olx.model.AdvertiseCategory;
import com.app.olx.model.StatusList;
import com.app.olx.repo.CategoryRepo;
import com.app.olx.repo.StatusListRepo;

@Service
public class MasterDataServiceImpl implements MasterDataService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private StatusListRepo statusListRepo;
	
	@Autowired
	private UserServiceDelegate userServiceDelegate;
	
	@Override
	public List<AdvertiseCategory> getAllAdvertiseCategories(String authToken) {
		if(!userServiceDelegate.isLoggedInUser(authToken))
			throw new InvalidAuthorizationTokenException(authToken);
		List<CategoryEntity> listOfCategory = categoryRepo.findAll();
		List<AdvertiseCategory> listOfAdvertiseCategory = new ArrayList<>();
		listOfCategory.stream().forEach(s -> 
		listOfAdvertiseCategory.add(new AdvertiseCategory(s.getId(), s.getCategory())));
		
		return listOfAdvertiseCategory;
	}

	@Override
	public List<StatusList> getAllPossibleAdvertiseStatus(String authToken) {
		if(!userServiceDelegate.isLoggedInUser(authToken))
			throw new InvalidAuthorizationTokenException(authToken);
		List<StatusListEntity> listOfCategory = statusListRepo.findAll();
		List<StatusList> listOfAdvertiseCategory = new ArrayList<>();
		listOfCategory.stream().forEach(s -> 
		listOfAdvertiseCategory.add(
				new StatusList(s.getId(), s.getStatus()))
		);
		
		return listOfAdvertiseCategory;
	}

}
