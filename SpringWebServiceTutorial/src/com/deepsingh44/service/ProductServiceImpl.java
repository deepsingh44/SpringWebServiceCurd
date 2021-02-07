package com.deepsingh44.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepsingh44.dao.ProductDao;
import com.deepsingh44.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public String insert(Product product) {
		long callback = productDao.insert(product);
		return (callback > 0) ? "Successfully Inserted" : "Insertion Failed";
	}

	@Override
	public String update(Product product) {
		int i = productDao.update(product);
		return (i > 0) ? "Successfully Update" : "Update Failed";

	}

	@Override
	public String delete(int id) {
		int i = productDao.delete(id);
		return (i > 0) ? "Successfully Delete" : "Deleted Failed";
	}

	@Override
	public List<Product> fetchAll() {
		List<Product> products = productDao.fetchAll();
		return (products != null) ? (products.size() > 0) ? products : null : null;
	}

	@Override
	public Product fetchById(int id) {
		Product product=productDao.fetchById(id);
		return(product!=null)?product:null;
	}

}
