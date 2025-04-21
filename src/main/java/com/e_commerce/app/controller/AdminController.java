package com.e_commerce.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.e_commerce.app.entity.Category;
import com.e_commerce.app.service.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public String index() {
		return "admin/index";
	}

	@GetMapping("/loadAddProduct")
	public String loadAddProduct() {
		return "admin/add_product";
	}

	@GetMapping("/category")
	public String category() {
		return "admin/category";
	}

	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute Category category,@RequestParam("file") MultipartFile file,  HttpSession session) throws IOException {
		
		String imageName = file != null ? file.getOriginalFilename() : "default.jpg";
		category.setImageName(imageName);
		
		Boolean existCategory = categoryService.existCategory(category.getName());
		if (existCategory) {
			session.setAttribute("errorMsg", "Category Name is already exists");
		} else {
			Category saveCategory = categoryService.saveCategory(category);
			if (ObjectUtils.isEmpty(saveCategory)) {
				session.setAttribute("errorMsg", "Not saved! Internal Server error!!");
			} else {
				
				File savefile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(savefile.getAbsolutePath()+File.separator+"category_img"+File.separator+file.getOriginalFilename());
				System.out.println(path);
				Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
				
				session.setAttribute("succMsg", "saved successfully!!");
			}
		}

		this.categoryService.saveCategory(category);
		return "redirect:/admin/category";
	}

}
