package com.paparising.receiptmanager.service;

import com.paparising.receiptmanager.domain.Receipt;
import com.paparising.receiptmanager.repository.ReceiptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Receipt.
 */
@Service
@Transactional
public class ReceiptService {

    private final Logger log = LoggerFactory.getLogger(ReceiptService.class);
    
    @Inject
    private ReceiptRepository receiptRepository;

    /**
     * Save a receipt.
     *
     * @param receipt the entity to save
     * @return the persisted entity
     */
    public Receipt save(Receipt receipt) {
        log.debug("Request to save Receipt : {}", receipt);
        Receipt result = receiptRepository.save(receipt);
        return result;
    }

    /**
     *  Get all the receipts.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Receipt> findAll(Pageable pageable) {
        log.debug("Request to get all Receipts");
        Page<Receipt> result = receiptRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one receipt by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Receipt findOne(Long id) {
        log.debug("Request to get Receipt : {}", id);
        Receipt receipt = receiptRepository.findOne(id);
        return receipt;
    }

    /**
     *  Delete the  receipt by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Receipt : {}", id);
        receiptRepository.delete(id);
    }
}
