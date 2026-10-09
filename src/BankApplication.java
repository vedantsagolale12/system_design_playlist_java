import model.BankAccount;
import model.Transaction;
import repository.AccountRepository;
import repository.InMemoryAccountRepository;
import service.BankService;
import service.BankServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class BankApplication {
    private final BankService bankService;
    private final Scanner scanner;

    public BankApplication(BankService bankService, Scanner scanner) {
        this.bankService = bankService;
        this.scanner = scanner;
    }

    public void start() {

        boolean running = true;

        while (running) {
            printMenu();

            int choice = readInt("Choose an option: ");

            try {
                switch (choice) {
                    case 1 -> createAccount();
                    case 2 -> deposit();
                    case 3 -> withdraw();
                    case 4 -> showBalance();
                    case 5 -> showTransactions();
                    case 0 -> running = false;
                    default -> System.out.println(
                            "Invalid menu option.");
                }
            } catch (IllegalArgumentException
                     | IllegalStateException exception) {

                System.out.println(
                        "Error: " + exception.getMessage());
            }
        }

        System.out.println("Thank you for using the bank.");
    }

    private void printMenu() {
        System.out.println("\n=== BANKING SYSTEM ===");
        System.out.println("1. Create account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show balance");
        System.out.println("5. Show transactions");
        System.out.println("0. Exit");
    }

    private void createAccount() {

        System.out.print("Customer name: ");
        String name = scanner.nextLine();

        BankAccount account =
                bankService.createAccount(name);

        System.out.println("Account created successfully.");
        System.out.println(
                "Account number: "
                        + account.getAccountNumber());
        System.out.println(
                "Customer: "
                        + account.getCustomer().getName());
        System.out.println(
                "Opening balance: "
                        + account.getBalance());
    }

    private void deposit() {
        long accountNumber = readLong("Account number: ");
        BigDecimal amount = readAmount("Deposit amount: ");

        bankService.deposit(accountNumber, amount);

        System.out.println("Deposit successful.");
        System.out.println(
                "Updated balance: "
                        + bankService.getBalance(accountNumber));
    }

    private void withdraw() {
        long accountNumber = readLong("Account number: ");
        BigDecimal amount = readAmount("Withdrawal amount: ");

        bankService.withdraw(accountNumber, amount);

        System.out.println("Withdrawal successful.");
        System.out.println(
                "Updated balance: "
                        + bankService.getBalance(accountNumber));
    }

    private void showBalance() {
        long accountNumber = readLong("Account number: ");

        System.out.println(
                "Balance: "
                        + bankService.getBalance(accountNumber));
    }

    private void showTransactions() {
        long accountNumber = readLong("Account number: ");

        List<Transaction> transactions =
                bankService.getTransactions(accountNumber);

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(
                    "ID: " + transaction.getTransactionId()
                            + " | Type: " + transaction.getType()
                            + " | Amount: " + transaction.getAmount()
                            + " | Balance after: "
                            + transaction.getBalanceAfter()
                            + " | Time: " + transaction.getTimestamp()
            );
        }
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(
                        scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println(
                        "Please enter a valid whole number.");
            }
        }
    }

    private long readLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(
                        scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println(
                        "Please enter a valid account number.");
            }
        }
    }

    private BigDecimal readAmount(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return new BigDecimal(
                        scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println(
                        "Please enter a valid monetary amount.");
            }
        }
    }

    public static void main(String[] args){
        AccountRepository repository =
                new InMemoryAccountRepository();

        BankService bankService =
                new BankServiceImpl(repository);

        try (Scanner scanner = new Scanner(System.in)) {
            BankApplication application =
                    new BankApplication(bankService, scanner);

            application.start();
        }
    }
    }

