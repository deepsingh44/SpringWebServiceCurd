package com.deepsingh44.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deepsingh44.model.Product;
import com.deepsingh44.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/insert")
	public String insert(@RequestBody() Product product) {
		return productService.insert(product);
	}

	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> fetchAll() {
		return productService.fetchAll();
	}

	@PutMapping("/update")
	public String update(@RequestBody() Product product) {
		return productService.update(product);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable() Integer id) {
		return productService.delete(id);
	}

}
