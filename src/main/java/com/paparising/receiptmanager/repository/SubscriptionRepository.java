package com.paparising.receiptmanager.repository;

import com.paparising.receiptmanager.domain.Subscription;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Subscription entity.
 */
@SuppressWarnings("unused")
public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {

}
