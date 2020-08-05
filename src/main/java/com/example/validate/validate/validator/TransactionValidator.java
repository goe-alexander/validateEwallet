package com.example.validate.validate.validator;

import com.example.validate.validate.Exceptions.InexistentTransactionType;
import com.example.validate.validate.Exceptions.InvalidIbanException;
import com.example.validate.validate.dto.TransactionDTO;
import com.example.validate.validate.dto.TransactionType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.IBANValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionValidator {
    private final IBANValidator ibanValidator;
    public boolean isTransactionValid(TransactionDTO transactionDTO) {
        sanitizeTypeAndVerify(transactionDTO);
        verifyIbanCorrectness(transactionDTO.getIban());
        return true;
    }

    private void sanitizeTypeAndVerify(TransactionDTO transactionDTO){
        String sanitizedTransactionType = transactionDTO.getType().trim().toUpperCase();
        if(TransactionType.contains(sanitizedTransactionType)){
            transactionDTO.setType(sanitizedTransactionType);
            return;
        }
        throw new InexistentTransactionType("Type of transaction not supported: " + transactionDTO.getType());
    }

    private void verifyIbanCorrectness(String iban) {
        if(ibanValidator.isValid(iban)) {
            return;
        };
        throw new InvalidIbanException("IBAN: " + iban + " is invalid!");
    }
}
