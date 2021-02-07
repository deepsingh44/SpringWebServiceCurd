package com.deepsingh44.service;

import java.util.List;

import com.deepsingh44.model.Product;

public interface ProductService {

	String insert(Product product);

	String update(Product product);

	String delete(int id);

	List<Product> fetchAll();

	Product fetchById(int id);
	
}
