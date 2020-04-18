package com.acms.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.acms.models.Product;
import com.acms.repositories.ProductRepository;

@Service
public class ProductService {
	
	public Iterable<Product> getAll(){
		
		return null;
		
	}
	
	public Product getById(){
		
		return null;
		
	}
	
	public Product getByName(){
		
		return null;
		
	}
	
	public String postData(int serialNumber,String productId,int timeStamp,String description,String name,double MRP,int quantity,double promotion) {
		Product per= new Product(serialNumber,productId,timeStamp,description,name,MRP,quantity,promotion);
		Configuration con = new Configuration().configure().addAnnotatedClass(Product.class);
		SessionFactory sf= con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx= session.beginTransaction();
		session.save(per);
		tx.commit();
		return productId;
	}
	
}
