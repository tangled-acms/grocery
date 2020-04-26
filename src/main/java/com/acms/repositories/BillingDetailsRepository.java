package com.acms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acms.models.BillingDetails;

@Repository
public interface BillingDetailsRepository extends JpaRepository<BillingDetails, Integer> {

}
