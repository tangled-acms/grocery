package com.acms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acms.models.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer>{

}
