package com.deepsingh44.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deepsingh44.dao.ProductDao;
import com.deepsingh44.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public long insert(Product product) {
		return (int) sessionFactory.getCurrentSession().save(product);
	}

	@Override
	@Transactional
	public int delete(int id) {
		int i = 0;
		Product product = sessionFactory.getCurrentSession().get(Product.class, id);
		if (product != null) {
			sessionFactory.getCurrentSession().delete(product);
			i = 1;
		}
		return i;
	}

	@Override
	@Transactional
	public int update(Product product) {
		int i = 0;
		Product currentproduct = sessionFactory.getCurrentSession().get(Product.class, product.getId());
		if (currentproduct != null) {
			//currentproduct = product;
			currentproduct.setName(product.getName());
			currentproduct.setPrice(product.getPrice());
			sessionFactory.getCurrentSession().update(currentproduct);
			i = 1;
		}
		return i;
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public List<Product> fetchAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
		return criteria.list();
	}

	@Override
	@Transactional
	public Product fetchById(int id) {
		Product product = sessionFactory.getCurrentSession().get(Product.class, id);
		return product;
	}
}
