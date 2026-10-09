package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Collections;

public class BankAccount {

    private final long accountNumber;
    private final Customer customer;

    private BigDecimal balance = BigDecimal.ZERO;

    private final List<Transaction> transactions =
            new ArrayList<>();

    public BankAccount(long accountNumber, Customer customer) {
        if (accountNumber <= 0) {
            throw new IllegalArgumentException(
                    "Account number must be positive");
        }

        this.accountNumber = accountNumber;
        this.customer = Objects.requireNonNull(customer);
    }

    public void deposit(
            BigDecimal amount, long transactionId) {

        validateAmount(amount);

        balance = balance.add(amount);

        recordTransaction(
                transactionId,
                TransactionType.DEPOSIT,
                amount
        );
    }

    public void withdraw(
            BigDecimal amount, long transactionId) {

        validateAmount(amount);

        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException(
                    "Insufficient balance");
        }

        balance = balance.subtract(amount);

        recordTransaction(
                transactionId,
                TransactionType.WITHDRAWAL,
                amount
        );
    }

    private void validateAmount(BigDecimal amount) {
        Objects.requireNonNull(amount, "Amount is required");

        if (amount.signum() <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero");
        }
    }

    private void recordTransaction(
            long transactionId,
            TransactionType type,
            BigDecimal amount) {

        Transaction transaction = new Transaction(
                transactionId,
                type,
                amount,
                balance
        );

        transactions.add(transaction);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(
                new ArrayList<>(transactions));
    }
}
