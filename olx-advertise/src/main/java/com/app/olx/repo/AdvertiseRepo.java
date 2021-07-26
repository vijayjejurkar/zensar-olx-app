package com.app.olx.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.olx.entity.AdvertiseEntity;

public interface AdvertiseRepo extends JpaRepository<AdvertiseEntity, Integer>{
	
	@Query("SELECT a FROM AdvertiseEntity a where a.title LIKE CONCAT('%',:searchText,'%')")
	List<AdvertiseEntity> findAdvertiseBySearchText(@Param("searchText") String searchText);

}
