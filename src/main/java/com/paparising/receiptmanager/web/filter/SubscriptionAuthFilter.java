package com.paparising.receiptmanager.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;

import org.springframework.stereotype.Component;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class SubscriptionAuthFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionAuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        
        // To do handle the authentication defined by APPDirect
        
        String authType = httpRequest.getAuthType();

        if (StringUtils.isBlank(authType)) {
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // Nothing to destroy
    }
}
