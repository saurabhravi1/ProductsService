package com.estore.ProductsService.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.estore.ProductsService.filter.AuthorizationFilter;

@Configuration
public class FilterConfig {	
	@Bean
	public FilterRegistrationBean<AuthorizationFilter> filterRegistrationBean(){		
		FilterRegistrationBean<AuthorizationFilter> reg = new FilterRegistrationBean<>();		
		reg.setFilter(new AuthorizationFilter());
		reg.setOrder(2);		
		reg.setUrlPatterns(Arrays.asList("/products/*","/products/"));
		return reg;
	}
	
}
