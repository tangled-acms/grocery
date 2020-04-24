package com.acms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acms.models.Retailer;

@Repository
public interface RetailerRepository extends JpaRepository<Retailer,String>{
 

}
