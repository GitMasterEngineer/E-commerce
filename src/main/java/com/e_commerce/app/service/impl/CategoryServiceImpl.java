package com.e_commerce.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.e_commerce.app.model.Category;
import com.e_commerce.app.repositories.CategoryRepo;
import com.e_commerce.app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;

//	@Override
//	public Category saveCategory(Category category) {
//		Category saveCat = this.categoryRepo.save(category);
//		return saveCat;
//	}
	
	@Override
	public Category saveCategory(Category category) {
	    if (category == null) {
	        throw new IllegalArgumentException("Category entity must not be null");
	    }

	    return this.categoryRepo.save(category);
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

	@Override
	public Boolean deleteCategory(int id) {
		Category category = categoryRepo.findById(id).orElse(null);
		if(!ObjectUtils.isEmpty(category))
		{
			categoryRepo.delete(category);
			return true;
		}
		return false;
		
	}

	@Override
	public Category getCategoryById(int id) {
		Category category = categoryRepo.findById(id).orElse(null);
		return category;
	}

}
