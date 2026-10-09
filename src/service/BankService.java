package service;

import model.BankAccount;
import model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface BankService {
    BankAccount createAccount(String customerName);
    void deposit(long accountNumber, BigDecimal amount);
    void withdraw(long accountNumber,BigDecimal amount);
    BigDecimal getBalance(long accountNumber);
    List<Transaction> getTransactions(long accountNumber);
}
