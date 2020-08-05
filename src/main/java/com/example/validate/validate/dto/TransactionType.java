package com.example.validate.validate.dto;

import java.util.Arrays;
import java.util.stream.Stream;

public enum TransactionType {
    IBAN_TO_IBAN,
    IBAN_TO_WALLET,
    WALLET_TO_IBAN,
    WALLET_TO_WALLET;

    public static boolean contains(String target) {
        for(TransactionType type : TransactionType.values()) {
            if(type.name().equalsIgnoreCase(target)){
                return true;
            }
        }
        return false;
    }
}
