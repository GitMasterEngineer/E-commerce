package com.e_commerce.app.service;

import java.util.List;

import com.e_commerce.app.entity.Category;

public interface CategoryService {

	Category saveCategory(Category category);
	
	Boolean existCategory(String name);

	List<Category> getAllCategory();
}
