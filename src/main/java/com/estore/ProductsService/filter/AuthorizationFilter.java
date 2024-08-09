package com.estore.ProductsService.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;


public class AuthorizationFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("Init AuthorizationFilter called");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("doFilter AuthorizationFilter called");
		logger.info("host:{}",request.getRemoteHost());
		chain.doFilter(request, response);
	}

}
