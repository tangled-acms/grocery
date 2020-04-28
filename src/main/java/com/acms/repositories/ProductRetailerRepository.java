package com.acms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acms.models.ProductRetailer;
import com.acms.models.ProductRetailerEmbeddedId;

@Repository
public interface ProductRetailerRepository extends JpaRepository<ProductRetailer,ProductRetailerEmbeddedId>{
	List<ProductRetailer> findByProductRetailerEmbeddedIdProductId(String ProductId);
	List<ProductRetailer> findByProductRetailerEmbeddedIdRetailerId(String RetailerId);

}
