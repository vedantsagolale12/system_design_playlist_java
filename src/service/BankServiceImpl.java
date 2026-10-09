package service;

import model.BankAccount;
import model.Customer;
import model.Transaction;
import repository.AccountRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class BankServiceImpl implements BankService {
    private final AccountRepository repository;
    private final AtomicLong accountSequence =
            new AtomicLong(100);
    private final AtomicLong customerSequence =
            new AtomicLong(1);
    private final AtomicLong transactionSequence =
            new AtomicLong(1);

    public BankServiceImpl(AccountRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("repository cannot be  null");
        }
        this.repository = repository;
    }


    @Override
    public BankAccount createAccount(String customerName) {
        long customerId = customerSequence.getAndIncrement();
        long accountNumber = accountSequence.getAndIncrement();
        Customer customer = new Customer(
                customerId, customerName);
        BankAccount account = new BankAccount(
                accountNumber, customer);
        repository.save(account);
        return account;
    }

    @Override
    public void deposit(long accountNumber, BigDecimal amount) {
        BankAccount account = findAccount(accountNumber);
        long transactionId =
                transactionSequence.getAndIncrement();
        account.deposit(amount, transactionId);
    }

    @Override
    public void withdraw(
            long accountNumber, BigDecimal amount) {
        BankAccount account = findAccount(accountNumber);
        long transactionId =
                transactionSequence.getAndIncrement();
        account.withdraw(amount, transactionId);
    }

    @Override
    public BigDecimal getBalance(long accountNumber) {
        return findAccount(accountNumber).getBalance();
    }

    @Override
    public List<Transaction> getTransactions(
            long accountNumber) {
        return findAccount(accountNumber).getTransactions();
    }

    private BankAccount findAccount(long accountNumber) {
        if (accountNumber <= 0) {
            throw new IllegalArgumentException(
                    "Account number must be positive");
        }

        return repository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Account not found: "
                                        + accountNumber));
    }
}
