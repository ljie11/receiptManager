package com.paparising.receiptmanager.repository;

import com.paparising.receiptmanager.domain.Item;
import com.paparising.receiptmanager.domain.Order;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Item entity.
 */
@SuppressWarnings("unused")
public interface ItemRepository extends JpaRepository<Item,Long> {

}
