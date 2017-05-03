package com.paparising.receiptmanager.service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paparising.receiptmanager.domain.Subscription;
import com.paparising.receiptmanager.repository.CreatorRepository;
import com.paparising.receiptmanager.repository.PayloadRepository;
import com.paparising.receiptmanager.repository.SubscriptionRepository;
import com.paparising.receiptmanager.service.dto.SubscriptionDTO;

/**
 * Service Implementation for managing subscription.
 */
@Service
@Transactional
public class SubscriptionService {

    private final Logger log = LoggerFactory.getLogger(SubscriptionService.class);
    
    @Inject
    private SubscriptionRepository subscriptionRepository;
    @Inject
    private CreatorRepository creatorRepository;
    @Inject
    private PayloadRepository payloadRepository;
    
    /**
     * create a subscription with give info
     * @param subscriptionStr
     * @return
     */
    public String createSubscription(String subscriptionStr) {
        String identityStr = "";
        
        try {
            SubscriptionDTO dto = SubscriptionDTO.fromJsonString(subscriptionStr);
            payloadRepository.save(dto.getPayload());
            creatorRepository.save(dto.getCreator());
            
            Subscription subscription = new Subscription();
            subscription.setActive(true);
            subscription.setCreated_date(ZonedDateTime.now());
            String uuid = UUID.randomUUID().toString();
            subscription.setUuid(uuid);
            subscriptionRepository.save(subscription);
            identityStr = uuid;
            
        } catch (IOException e) {
            log.error(e.getMessage());
            return identityStr;
        }
        return identityStr;
    }
    
    /**
     * 
     * @param subscriptionStr
     * @return
     */
    public String cancelSubscription(String subscriptionStr) {
        String identityStr = "";
        
        try {
            SubscriptionDTO dto = SubscriptionDTO.fromJsonString(subscriptionStr);
            payloadRepository.save(dto.getPayload());
            creatorRepository.save(dto.getCreator());
            
            Subscription subscription = new Subscription();
            subscription.setActive(true);
            subscription.setCreated_date(ZonedDateTime.now());
            String uuid = UUID.randomUUID().toString();
            subscription.setUuid(uuid);
            subscriptionRepository.save(subscription);
            identityStr = uuid;
            
        } catch (IOException e) {
            log.error(e.getMessage());
            return identityStr;
        }
        return identityStr;
    }
    
}
