package com.e_commerce.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_commerce.app.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
