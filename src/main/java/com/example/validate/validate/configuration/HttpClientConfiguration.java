package com.example.validate.validate.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfiguration {

    @Value("${rest.read.timeout}")
    private int readTimeout;

    @Value("${rest.connect.timeout}")
    private int connectionTimeout;

    @Bean
    public RestTemplate customRestTemplate() {
        return new RestTemplate((clientHttpRequestFactory()));
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(readTimeout);
        factory.setConnectionRequestTimeout(readTimeout);
        return factory;
    }
}
