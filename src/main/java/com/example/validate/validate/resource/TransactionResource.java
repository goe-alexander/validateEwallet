package com.example.validate.validate.resource;

import com.example.validate.validate.dto.TransactionDTO;
import com.example.validate.validate.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionResource {
    private final TransactionService transactionService;

    @PostMapping
    void createTransaction(@Valid @RequestBody(required = true)TransactionDTO transactionDTO) throws InterruptedException{
        transactionService.validateAndPersist(transactionDTO);
    }
}
