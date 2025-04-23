package com.e_commerce.app.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.e_commerce.app.model.Product;
import com.e_commerce.app.repositories.ProductRepo;
import com.e_commerce.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Override
	public Product saveProduct(Product product) {
		Product saveProduct = productRepo.save(product);
		return saveProduct;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> allProducts = productRepo.findAll();
		return allProducts;
	}

	@Override
	public Boolean deleteProduct(Integer id) {
		Product product = productRepo.findById(id).orElse(null);
		if (!ObjectUtils.isEmpty(product)) {
			productRepo.delete(product);
			return true;
		}
		return false;
	}

	@Override
	public Product getProductById(Integer id) {
		Product product = productRepo.findById(id).orElse(null);
		return product;
	}

	@Override
	public Product updateProduct(Product product, MultipartFile image){
		
		Product dbproduct = getProductById(product.getId());

		String imageName = image.isEmpty() ? dbproduct.getImage() : image.getOriginalFilename();

		dbproduct.setTitle(product.getTitle());
		dbproduct.setDescription(product.getDescription());
		dbproduct.setCategory(product.getCategory());
		dbproduct.setPrice(product.getPrice());
		dbproduct.setStock(product.getStock());
		dbproduct.setImage(imageName);
		Product updateProduct = productRepo.save(dbproduct);

		if (!ObjectUtils.isEmpty(updateProduct)) {
			if (!image.isEmpty()) {
				try {
				File savefile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + "product_img" + File.separator
						+ image.getOriginalFilename());
				
				Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			return product;
		}
		return null;
	}

}
