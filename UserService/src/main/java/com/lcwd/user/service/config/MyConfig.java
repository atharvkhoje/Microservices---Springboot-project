package com.lcwd.user.service.config;

import java.util.ArrayList;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.config.interceptor.RestTemplateInterceptor;

@Configuration
public class MyConfig {

	
	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;
	
	
	@Autowired
	private OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;
	
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{
		
		 RestTemplate restTemplate = new RestTemplate();
		 
		 List<ClientHttpRequestInterceptor> interceptor = new ArrayList<>();
		 interceptor.add(new RestTemplateInterceptor(manager(
				 clientRegistrationRepository, auth2AuthorizedClientRepository
				 )));
		 
		 restTemplate.setInterceptors(interceptor);
		 
		return restTemplate; 
	}
	
	
	
	// declare the bean of OAuth2AuthorizedClient manager
	@Bean
	public OAuth2AuthorizedClientManager manager(
			ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository
			)
	{
		
		OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
		DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, oAuth2AuthorizedClientRepository);
		defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
		return defaultOAuth2AuthorizedClientManager;
	}

}
