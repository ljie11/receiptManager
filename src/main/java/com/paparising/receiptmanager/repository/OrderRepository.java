package com.paparising.receiptmanager.repository;

import com.paparising.receiptmanager.domain.Order;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the order entity.
 */
@SuppressWarnings("unused")
public interface OrderRepository extends JpaRepository<Order,Long> {

}
