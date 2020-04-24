package com.acms.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acms.models.Product;
import com.acms.repositories.ProductRepository;

@Service
public class ProductService {

	// @Autowired
	// ProductRepository productRepository;

	public List<Product> getAll() {

		List<Product> products = new ArrayList<Product>();
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		products = (session.createQuery("from Product", Product.class)).list();
		session.getTransaction().commit();
		session.close();
		return products;

	}

	public Product getById(String productId) {
		Product productObj = new Product();
		Configuration con = new Configuration().configure().addAnnotatedClass(Product.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		productObj = (Product) session.get(Product.class, productId);
		tx.commit();
		return productObj;

	}

	public Product getByName() {
		return null;
	}

	public String postData(String productId, String description, String name, double MRP, int quantity,
			double promotion) {
		Product productObj = new Product();
		productObj.setProductId(productId);
		productObj.setDescription(description);
		productObj.setName(name);
		productObj.setMRP(MRP);
		productObj.setQuantity(quantity);
		productObj.setQuantity(quantity);
		productObj.setPromotion(promotion);
		Configuration con = new Configuration().configure().addAnnotatedClass(Product.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(productObj);
		tx.commit();
		return productId;
	}

}
