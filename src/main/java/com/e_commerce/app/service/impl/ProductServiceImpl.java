package com.e_commerce.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.app.model.Product;
import com.e_commerce.app.repositories.ProductRepo;
import com.e_commerce.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public Product saveProduct(Product product) {
		Product saveProduct = productRepo.save(product);
		return saveProduct;
	}

}
