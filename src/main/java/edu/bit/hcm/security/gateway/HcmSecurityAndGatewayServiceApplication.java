package edu.bit.hcm.security.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.bit.hcm.security.gateway.filter.pre.SimpleFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@Configuration
public class HcmSecurityAndGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcmSecurityAndGatewayServiceApplication.class, args);
	}

	@Bean
	public SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}

}
