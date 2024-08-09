package com.estore.ProductsService;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.estore.ProductsService.command.CreateProductCommandInterceptor;
import com.estore.ProductsService.core.errorhandling.ProductsServiceEventsErrorHandler;
import com.estore.core.AxonXstreamConfig;

@EnableDiscoveryClient
@SpringBootApplication
@Import({AxonXstreamConfig.class })
@EnableAspectJAutoProxy
public class ProductsServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductsServiceApplication.class, args);
	}
	@Autowired
	public void registerCreateProductCommandInterceptor(ApplicationContext applicationConext, CommandBus commandBus) {
		CreateProductCommandInterceptor createProductCommandInterceptor = applicationConext
				.getBean(CreateProductCommandInterceptor.class);
		commandBus.registerDispatchInterceptor(createProductCommandInterceptor);
	}	
	@Autowired
	public void configure(EventProcessingConfigurer config) {
		config.registerListenerInvocationErrorHandler("product-group"
				,confguration->new ProductsServiceEventsErrorHandler());
	}
}
