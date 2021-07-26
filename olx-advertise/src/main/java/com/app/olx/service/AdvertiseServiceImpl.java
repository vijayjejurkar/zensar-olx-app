package com.app.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.olx.entity.AdvertiseEntity;
import com.app.olx.exception.InvalidAdvertiseException;
import com.app.olx.exception.InvalidAuthorizationTokenException;
import com.app.olx.model.Advertise;
import com.app.olx.repo.AdvertiseRepo;

@Service
public class AdvertiseServiceImpl implements AdvertiseService{

	@Autowired
	private AdvertiseRepo advertiseRepo;
	
	@Autowired
	private UserServiceDelegate userServiceDelegate;
	
	@Override
	public Advertise createAdvertise(Advertise advertise, String authToken) {
		if(!userServiceDelegate.isLoggedInUser(authToken))
			throw new InvalidAuthorizationTokenException(authToken);
		AdvertiseEntity advertiseEntity = new AdvertiseEntity(advertise.getTitle(), 
				advertise.getPrice(), 
				advertise.getCategory(), 
				advertise.getDescription(), 
				advertise.getUserName(), 
				advertise.getCreatedDate(), 
				advertise.getModifiedDate(), 
				advertise.getStatus(), 
				advertise.getPostedBy());
		AdvertiseEntity advertiseAdded = advertiseRepo.save(advertiseEntity);
		
		advertise.setId(advertiseAdded.getId());
		return advertise;
	}

	@Override
	public List<Advertise> getAllAdvertisePostedByLoggedInUser(String authToken) {
		if(!userServiceDelegate.isLoggedInUser(authToken))
			throw new InvalidAuthorizationTokenException(authToken);
		List<AdvertiseEntity> listOfAdvertise = advertiseRepo.findAll();
		List<Advertise> advertiseList = new ArrayList<>();
		listOfAdvertise.stream().forEach(s -> 
		advertiseList.add(new Advertise(s.getId(),
				s.getTitle(), 
				s.getPrice(),
				s.getCategory(),
				s.getDescription(),
				s.getUserName(),
				s.getCreatedDate(),
				s.getModifiedDate(),
				s.getStatus(),
				s.getPostedBy()))
				);
		return advertiseList;
	}

	@Override
	public Advertise getAllAdvertisePostedByLoggedInUser(Integer postId, String authToken) {
		if(!userServiceDelegate.isLoggedInUser(authToken))
			throw new InvalidAuthorizationTokenException(authToken);
		Optional<AdvertiseEntity> advertiseEntity = advertiseRepo.findById(postId);

		if(advertiseEntity.isPresent()) {
			AdvertiseEntity entity = advertiseEntity.get();
			return new Advertise(entity.getId(), 
					entity.getTitle(), 
					entity.getPrice(), 
					entity.getCategory(), 
					entity.getDescription(), 
					entity.getUserName(), 
					entity.getCreatedDate(), 
					entity.getModifiedDate(), 
					entity.getStatus(), 
					entity.getPostedBy());		
		}
		throw new InvalidAdvertiseException("Invalid id = "+postId);
	}

	@Override
	public List<Advertise> findAdvertiseBySearchText(String searchText, String authToken) {
		if(!userServiceDelegate.isLoggedInUser(authToken))
			throw new InvalidAuthorizationTokenException(authToken);
		List<AdvertiseEntity> advertiseEntityList = advertiseRepo.findAdvertiseBySearchText(searchText);
		List<Advertise> listOfAdvertise = new ArrayList<>();
		if(advertiseEntityList != null && !advertiseEntityList.isEmpty()) {
			advertiseEntityList.stream().forEach(advertise -> 
			listOfAdvertise.add(new Advertise(advertise.getId(), 
					advertise.getTitle(), 
					advertise.getPrice(), 
					advertise.getCategory(), 
					advertise.getDescription(), 
					advertise.getUserName(), 
					advertise.getCreatedDate(), 
					advertise.getModifiedDate(), 
					advertise.getStatus(), 
					advertise.getPostedBy())));
			return listOfAdvertise;
		}
		throw new InvalidAdvertiseException();
	}

	@Override
	public Advertise updateAdvertise(Integer id, Advertise advertise, String authToken) {
		if(!userServiceDelegate.isLoggedInUser(authToken))
			throw new InvalidAuthorizationTokenException(authToken);
		Optional<AdvertiseEntity> advertiseEntity = advertiseRepo.findById(id);
		if(advertiseEntity.isPresent()) {
			advertiseEntity.get().setCategory(advertise.getCategory());
			advertiseEntity.get().setCreatedDate(advertise.getCreatedDate());
			advertiseEntity.get().setDescription(advertise.getDescription());
			advertiseEntity.get().setModifiedDate(advertise.getModifiedDate());
			advertiseEntity.get().setPostedBy(advertise.getPostedBy());
			advertiseEntity.get().setPrice(advertise.getPrice());
			advertiseEntity.get().setStatus(advertise.getStatus());
			advertiseEntity.get().setTitle(advertise.getTitle());
			advertiseEntity.get().setUserName(advertise.getUserName());
			
			advertiseRepo.save(advertiseEntity.get());
			
			advertise.setId(advertiseEntity.get().getId());
			return advertise;
		}
		throw new InvalidAdvertiseException();
	}

	@Override
	public boolean deleteAdvertisePostedByLoggedInUser(Integer postId, String authToken) {
		if(!userServiceDelegate.isLoggedInUser(authToken))
			throw new InvalidAuthorizationTokenException(authToken);
		if(advertiseRepo.existsById(postId)) {
			advertiseRepo.deleteById(postId);
			return true;
		}
		throw new InvalidAdvertiseException();
	}

}
