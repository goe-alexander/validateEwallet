package com.example.validate.validate.service;

import com.example.validate.validate.Exceptions.UserNotFoundException;
import com.example.validate.validate.dto.TransactionDTO;
import com.example.validate.validate.validator.TransactionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.net.ConnectException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionService {

    private final PersistenceService persistenceService;
    private final TransactionValidator transactionValidator;

    @Retryable(value = {RestClientException.class, ConnectException.class}, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public void validateAndPersist(TransactionDTO transactionDTO) throws InterruptedException {
        System.out.println("Started Validation and Persistence");
        Long userCnp = transactionDTO.getCnp();
        // Will use the persistence layer as stand in for user authentication
        // Call is synchronous to ensure no persistence call is made unnecessarily
        Boolean userExists = persistenceService.doesUserExist(userCnp);
        if (!userExists) {
            throw new UserNotFoundException(userCnp);
        }
        transactionValidator.isTransactionValid(transactionDTO);
        persistenceService.persistActivity(transactionDTO);
    }

    @Recover
    public void simmulateRecovery(RestClientException exception) {
        // This is where some other service / a new instance of the same service
        // would be called if the previous call wouldn't work after retrying
        System.out.println("Recovery for transaction persistence triggered: ");
    }
}
