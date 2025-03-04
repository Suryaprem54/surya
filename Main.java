import java.util.Scanner;

// BankAccount class: Handles user balance and PIN verification
class BankAccount {
    private double balance;
    private final int pin;

    public BankAccount(double initialBalance, int pin) {
        this.balance = initialBalance;
        this.pin = pin;
    }

    public boolean verifyPin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposit successful! New balance: $" + balance);
        } else {
            System.out.println("❌ Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Withdrawal successful! New balance: $" + balance);
        } else {
            System.out.println("❌ Insufficient balance or invalid amount.");
        }
    }
}

// ATM class: Handles the user interface and interaction
class ATM {
    private final BankAccount account;
    private final Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.print("🔑 Enter your PIN: ");
        int enteredPin = scanner.nextInt();

        if (!account.verifyPin(enteredPin)) {
            System.out.println("❌ Incorrect PIN. Exiting...");
            return;
        }

        while (true) {
            showMenu();
            int choice = getChoice();

            switch (choice) {
                case 1 -> System.out.println("💰 Current Balance: $" + account.getBalance());
                case 2 -> handleDeposit();
                case 3 -> handleWithdrawal();
                case 4 -> {
                    System.out.println("🚪 Thank you for using the ATM! Have a great day!");
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n===== 🏦 ATM MENU =====");
        System.out.println("1️⃣ Check Balance");
        System.out.println("2️⃣ Deposit Money");
        System.out.println("3️⃣ Withdraw Money");
        System.out.println("4️⃣ Exit");
        System.out.print("👉 Choose an option: ");
    }

    private int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("❌ Invalid input! Please enter a number.");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

    private void handleDeposit() {
        System.out.print("💵 Enter deposit amount: ");
        double depositAmount = scanner.nextDouble();
        account.deposit(depositAmount);
    }

    private void handleWithdrawal() {
        System.out.print("💵 Enter withdrawal amount: ");
        double withdrawAmount = scanner.nextDouble();
        account.withdraw(withdrawAmount);
    }
}

// Main class: Entry point of the application
public class ATMSimulator {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(5000, 1234);  // Example balance & PIN
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
