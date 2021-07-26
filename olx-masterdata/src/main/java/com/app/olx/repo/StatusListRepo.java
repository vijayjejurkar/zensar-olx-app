package com.app.olx.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.olx.entity.StatusListEntity;

public interface StatusListRepo extends JpaRepository<StatusListEntity, Integer>{

}
