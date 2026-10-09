
# Banking Management System — Java Mini Project

A console-based Banking Management System built using Core Java to practice Object-Oriented Programming (OOP), clean code, and software design principles.

This project is an initial Proof of Concept (POC) that stores data in memory. Database persistence will be introduced in a future version.

## Features

- Create customer accounts
- Deposit money
- Withdraw money
- Check account balance
- View transaction history
- Validate transaction amounts
- Prevent withdrawals with insufficient funds
- Generate account and transaction IDs
- Store account data using Java collections

## Tech Stack

- **Language:** Java 17+
- **Interface:** Console-based application
- **Storage:** HashMap and ArrayList
- **Dependencies:** No external libraries required

## Project Structure

```text
src/main/java/com/bank/
├── app/
│   └── BankApplication.java
├── model/
│   ├── Customer.java
│   ├── BankAccount.java
│   ├── Transaction.java
│   └── TransactionType.java
├── repository/
│   ├── AccountRepository.java
│   └── InMemoryAccountRepository.java
└── service/
    ├── BankService.java
    └── BankServiceImpl.java
```

## How to Run

Compile the project from the root directory:

```bash
javac -d out $(find src/main/java -name "*.java")
```

Run the application:

```bash
java -cp out com.bank.app.BankApplication
```

## OOP Concepts Covered

### 1. Encapsulation
- Private fields protect internal state.
- Account balances are modified through controlled methods.
- Transaction records are immutable.

### 2. Abstraction
- `BankService` defines banking operations.
- `AccountRepository` defines account storage operations.

### 3. Polymorphism
- Service and repository implementations are accessed through interfaces.

### 4. Association
- `BankAccount` maintains a reference to its `Customer`.

### 5. Aggregation
- The repository maintains references to account objects without owning their entire lifecycle.

### 6. Composition
- `BankAccount` creates and manages its transaction history.

### 7. Low Coupling
- `BankServiceImpl` depends on the `AccountRepository` interface instead of a concrete storage implementation.

### 8. High Cohesion
- Each class has a clearly defined responsibility.
- Models represent domain entities.
- Services coordinate business operations.
- Repositories manage storage.
- The application handles user interaction.

## Java Best Practices Covered

- Private fields and controlled access
- Constructor-based dependency injection
- Interfaces for abstraction
- `BigDecimal` for monetary calculations
- Enums for transaction types
- `Optional` for potentially missing accounts
- Input validation and exception handling
- Separation of concerns
- Meaningful class and method names
- Immutable transaction records

## Architecture

The application follows a layered structure:

1. **Application Layer:** Handles console input and output.
2. **Service Layer:** Implements banking operations.
3. **Repository Layer:** Manages in-memory account storage.
4. **Model Layer:** Represents customers, accounts, and transactions.

The repository interface allows the storage implementation to be replaced with a database-backed implementation later.

## Current Limitations

- Data is lost when the application terminates.
- Database persistence is not implemented.
- Authentication and authorization are not implemented.
- The in-memory repository is not thread-safe.
- Production-grade transaction safety is not implemented.
- Monetary precision validation can be strengthened.

## Future Improvements

- Add unit tests using JUnit.
- Introduce database persistence using JDBC or Spring Data.
- Add logging and improved error handling.
- Enforce unique account numbers.
- Strengthen monetary validation.
- Add account types if their business rules differ.

## Learning Objectives

This project demonstrates how to apply Core Java and OOP principles to a practical problem while maintaining separation of concerns, cohesive classes, low coupling, and extensibility.

**Project Status:** Initial in-memory POC  
**Persistence:** Planned for a future version
```



