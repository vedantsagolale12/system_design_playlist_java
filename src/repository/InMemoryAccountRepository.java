package repository;

import model.BankAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryAccountRepository
        implements AccountRepository {

    private final Map<Long, BankAccount> accounts =
            new HashMap<>();

    @Override
    public void save(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
    }

    @Override
    public Optional<BankAccount> findByAccountNumber(
            long accountNumber) {

        return Optional.ofNullable(
                accounts.get(accountNumber));
    }

    @Override
    public List<BankAccount> findAll() {
        return new ArrayList<>(accounts.values());
    }
}

