package com.api.gateway.apigateway.wbSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	
	@Autowired
    private JwtAuthenticationFilter filter;
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("user-service",
                r -> r.path("/user/**")
                .filters(f -> f.filter(filter))
                .uri("lb://USER-SERVICE"))
				
				.route("product-service",
		                r -> r.path("/product/**")
		                .filters(f -> f.filter(filter))
		                .uri("lb://PRODUCT-SERVICE"))
				.route("order-service",
		                r -> r.path("/order/**")
		                .filters(f -> f.filter(filter))
		                .uri("lb://ORDER-SERVICE")).build();

		
	}

}
