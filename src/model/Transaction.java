package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private final long transactionId;
    private final TransactionType type;
    private final BigDecimal amount;
    private final BigDecimal balanceAfter;
    private final LocalDateTime timestamp;

    public Transaction(
            long transactionId,
            TransactionType type,
            BigDecimal amount,
            BigDecimal balanceAfter) {

        if (transactionId <= 0) {
            throw new IllegalArgumentException(
                    "Transaction ID must be positive");
        }

        this.transactionId = transactionId;
        this.type = Objects.requireNonNull(type);
        this.amount = Objects.requireNonNull(amount);
        this.balanceAfter = Objects.requireNonNull(balanceAfter);
        this.timestamp = LocalDateTime.now();
    }

    public long getTransactionId() {
        return transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

