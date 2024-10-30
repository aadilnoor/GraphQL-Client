package com.graphql.config;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQLClientConfig {

	@Autowired
	private Environment environment;
	
	@Bean
	public HttpGraphQlClient graphQlClient() {
		return HttpGraphQlClient.builder(webClient()).build();
	}
	
	public WebClient  webClient() {
		return WebClient
				.builder()
				.baseUrl(Objects
				.requireNonNull(environment
				.getProperty("graphql.server.url")))
				.build();
	}
}
