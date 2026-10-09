package repository;

import model.BankAccount;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    void save(BankAccount account);
    Optional<BankAccount> findByAccountNumber(
            long accountNumber);

    List<BankAccount> findAll();
}
