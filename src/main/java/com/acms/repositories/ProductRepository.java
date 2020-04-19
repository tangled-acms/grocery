package com.acms.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acms.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

}
