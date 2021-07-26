package com.app.olx.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.olx.entity.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer>{

}
