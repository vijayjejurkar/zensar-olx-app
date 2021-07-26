package com.app.olx.service;

import java.util.List;

import com.app.olx.model.Advertise;

public interface AdvertiseService {

	Advertise createAdvertise(Advertise advertise, String token);
	List<Advertise> getAllAdvertisePostedByLoggedInUser(String token);
	Advertise getAllAdvertisePostedByLoggedInUser(Integer postId, String token);
	List<Advertise> findAdvertiseBySearchText(String searchText, String token);
	Advertise updateAdvertise(Integer id, Advertise advertise, String token);
	boolean deleteAdvertisePostedByLoggedInUser(Integer postId, String token);
}
