package com.paparising.receiptmanager.repository;

import com.paparising.receiptmanager.domain.Company;
import com.paparising.receiptmanager.domain.Creator;
import com.paparising.receiptmanager.domain.Item;
import com.paparising.receiptmanager.domain.Order;
import com.paparising.receiptmanager.domain.Payload;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the creator entity.
 */
@SuppressWarnings("unused")
public interface CreatorRepository extends JpaRepository<Creator,Long> {

}
