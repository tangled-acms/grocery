package com.acms.services;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import com.acms.models.Product;
import com.acms.repositories.ProductRepository;

@Service
public class ProductService {
	
	//ProductRepository productRepository;
	

public Iterable<Product> getAll(){
            
            return null;
		
	}
	

	
	public Product getById(String productId){
		Product productObj= new Product();
		Configuration con = new Configuration().configure().addAnnotatedClass(Product.class);
		SessionFactory sf= con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx= session.beginTransaction();
		productObj=(Product) session.get(Product.class, productId);
		tx.commit();
		return productObj;
		
	}
	
	public Product getByName(){
		
		return null;
		
	}
	
	public String postData(int serialNumber,String productId,int timeStamp,String description,String name,double MRP,int quantity,double promotion) {
		Product productObj= new Product();
		productObj.setSerialNumber(serialNumber);
		productObj.setProductId(productId);
		productObj.setTimeStamp(timeStamp);
		productObj.setDescription(description);
		productObj.setName(name);
		productObj.setMRP(MRP);
		productObj.setQuantity(quantity);
		productObj.setQuantity(quantity);
		productObj.setPromotion(promotion);
		Configuration con = new Configuration().configure().addAnnotatedClass(Product.class);
		SessionFactory sf= con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx= session.beginTransaction();
		session.save(productObj);
		tx.commit();
		return productId;
	}
	
}
