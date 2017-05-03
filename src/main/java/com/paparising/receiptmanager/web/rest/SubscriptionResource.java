package com.paparising.receiptmanager.web.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.paparising.receiptmanager.service.SubscriptionService;
import com.paparising.receiptmanager.service.dto.SubscriptionResponseDTO;
import com.paparising.receiptmanager.web.rest.errors.URLConnectionErrorException;

/**
 * REST controller for managing App direct Subscription.
 */
@RestController
@RequestMapping("/api")
public class SubscriptionResource {

    private final Logger log = LoggerFactory.getLogger(SubscriptionResource.class);

    @Inject
    private SubscriptionService subscriptionService;
    
    
    @RequestMapping(value = "/subscriptions/create",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SubscriptionResponseDTO> receiveCreateNotification(@RequestParam(value = "url", required = true) String url) {
        String subscriptionStr = getSubstriptionDetails(url);
        if (StringUtils.isBlank(subscriptionStr)) {
            return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new SubscriptionResponseDTO(false,"","Error","Cannot fetch the subsription details.")); 
        }
        String uuid = subscriptionService.createSubscription(subscriptionStr);
        
        if (StringUtils.isBlank(uuid)) {
            return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new SubscriptionResponseDTO(false,"","Error","not able to create a subscription with given subscription data.")); 
        }
        
        return new ResponseEntity<>(new SubscriptionResponseDTO(true, uuid,"",""), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/subscriptions/cancel",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SubscriptionResponseDTO> receiveCancelNotification(@RequestParam(value = "url", required = true) String url) {
        String subscriptionStr = getSubstriptionDetails(url);
        if (StringUtils.isBlank(subscriptionStr)) {
            return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new SubscriptionResponseDTO(false,"","Error","Cannot fetch the subsription details.")); 
        }
        String uuid = subscriptionService.cancelSubscription(subscriptionStr);
        
        if (StringUtils.isBlank(uuid)) {
            return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new SubscriptionResponseDTO(false,"","Error","not able to cancel a subscription with given subscription data.")); 
        }
        
        return new ResponseEntity<>(new SubscriptionResponseDTO(true,"sampleIdentifyId","",""), HttpStatus.OK);
    }

    /**
     * 
     * @param urlStr
     * @return
     */
    private String getSubstriptionDetails(String urlStr) {
        
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        
        try {
            URL url = new URL(urlStr);
            urlConn = url.openConnection();
            
            if (urlConn != null) {
                urlConn.setReadTimeout(60 * 1000);
            }
            if (urlConn != null && urlConn.getInputStream() != null) {
                
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            if(null != in) {
                in.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new URLConnectionErrorException("Exception while calling URL:"+ urlStr);
        } 
        return sb.toString();
    }

}
