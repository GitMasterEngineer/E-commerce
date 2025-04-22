package com.e_commerce.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.e_commerce.app.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
	@Query
	public Boolean existsByName(String name);

}
