package com.e_commerce.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.app.entity.Category;
import com.e_commerce.app.repositories.CategoryRepo;
import com.e_commerce.app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category saveCategory(Category category) {
		Category saveCat = this.categoryRepo.save(category);
		return saveCat;
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> AllCat = this.categoryRepo.findAll();
		return AllCat;
	}

	@Override
	public Boolean existCategory(String name) {
		Boolean existName = this.categoryRepo.existsByName(name);
		return existName;
	}

}
