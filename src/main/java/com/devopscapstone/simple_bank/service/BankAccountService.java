package com.devopscapstone.simple_bank.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class BankAccountService {

    // In-memory state: resets to 0 when the app restarts
    private final AtomicReference<BigDecimal> balance = new AtomicReference<>(BigDecimal.ZERO);

    public BigDecimal getBalance() {
        return balance.get();
    }

    public void deposit(BigDecimal amount) {
        if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            balance.updateAndGet(current -> current.add(amount));
        }
    }

    public void withdraw(BigDecimal amount) {
        if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            balance.updateAndGet(current -> {
                if (current.compareTo(amount) >= 0) {
                    return current.subtract(amount);
                }
                throw new IllegalArgumentException("Insufficient funds.");
            });
        }
    }

}
