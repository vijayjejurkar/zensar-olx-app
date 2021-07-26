package com.app.olx.service;

import java.util.List;
import com.app.olx.model.AdvertiseCategory;
import com.app.olx.model.StatusList;

public interface MasterDataService {
	
	List<AdvertiseCategory> getAllAdvertiseCategories(String authToken);
	List<StatusList> getAllPossibleAdvertiseStatus(String authToken);

}
