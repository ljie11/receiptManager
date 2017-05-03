package com.paparising.receiptmanager.repository;

import com.paparising.receiptmanager.domain.Company;
import com.paparising.receiptmanager.domain.Item;
import com.paparising.receiptmanager.domain.Order;
import com.paparising.receiptmanager.domain.Payload;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the company entity.
 */
@SuppressWarnings("unused")
public interface CompanyRepository extends JpaRepository<Company,Long> {

}
