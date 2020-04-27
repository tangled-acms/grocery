package com.acms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acms.models.BillingDetails;
import com.acms.models.BillingDetailsEmbeddedId;

@Repository
public interface BillingDetailsRepository extends JpaRepository<BillingDetails, BillingDetailsEmbeddedId> {
	List<BillingDetails> findByBillingDetailsEmbeddedIdBillId(int billId);

}
