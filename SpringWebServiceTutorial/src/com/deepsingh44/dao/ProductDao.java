package com.deepsingh44.dao;

import java.util.List;

import com.deepsingh44.model.Product;

public interface ProductDao {
	
	long insert(Product product);

	int update(Product product);

	int delete(int id);

	List<Product> fetchAll();

	Product fetchById(int id);
}
