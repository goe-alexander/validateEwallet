package com.example.validate.validate.service;

import com.example.validate.validate.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class PersistenceService {
    @Value("${rest.user.cnp.verifier.uri}")
    private String userVerifierURI;

    @Value("${rest.transaction.persist.uri}")
    private String transactionPersistenceURI;

    @Autowired
    @Qualifier("customRestTemplate")
    private RestTemplate restTemplate;

    public boolean doesUserExist(Long cnp) {
        return restTemplate.getForObject(userVerifierURI + cnp, Boolean.class);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Void> persistActivity(TransactionDTO transactionDTO) throws InterruptedException {
        restTemplate.postForLocation(transactionPersistenceURI, transactionDTO);
        //test the async call simulating latency
        //Thread.sleep(2000);
        return CompletableFuture.completedFuture(null);
    }
}
