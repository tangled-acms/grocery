package com.acms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acms.models.Analytics;
import com.acms.models.AnalyticsEmbeddedId;

@Repository
public interface AnalyticsRepository extends JpaRepository<Analytics, AnalyticsEmbeddedId>{
	List<Analytics> findByAnalyticsEmbeddedIdProductId(String productId);

}
