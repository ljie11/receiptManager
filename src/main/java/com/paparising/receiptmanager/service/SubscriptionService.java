package com.paparising.receiptmanager.service;

import com.paparising.receiptmanager.domain.Subscription;
import com.paparising.receiptmanager.repository.SubscriptionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Service Implementation for managing subscription.
 */
@Service
@Transactional
public class SubscriptionService {

    private final Logger log = LoggerFactory.getLogger(SubscriptionService.class);
    
    @Inject
    private SubscriptionRepository subscriptionRepository;

    /**
     * Save a subscription.
     *
     * @param subscription the entity to save
     * @return the persisted entity
     */
    public Subscription save(Subscription subscription) {
        log.debug("Request to save subscription : {}", subscription);
        Subscription result = subscriptionRepository.save(subscription);
        return result;
    }

    /**
     *  Get all the subscriptions.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Subscription> findAll(Pageable pageable) {
        log.debug("Request to get all subscriptions");
        Page<Subscription> result = subscriptionRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one subscription by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Subscription findOne(Long id) {
        log.debug("Request to get subscription : {}", id);
        Subscription subscription = subscriptionRepository.findOne(id);
        return subscription;
    }

    /**
     *  Delete the  subscription by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete subscription : {}", id);
        subscriptionRepository.delete(id);
    }
}
