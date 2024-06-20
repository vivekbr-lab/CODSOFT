import java.util.*;

class ATM {
    int Balance=20000;

    public void withdraw(int amount) {
        System.out.println("Withdraw amount: " + amount);
        Balance -= amount;
    }

    public void deposite(int amount) {
        System.out.println("Deposited amount: " + amount);
        Balance += amount;
    }

    public void checkBalance() {
        System.out.println("Balance: " + Balance);
    }
}

class BankAccount extends ATM {
    Scanner sc = new Scanner(System.in);

    public void performOperations() {
        while (true) {
            System.out.println("Enter number (1: Withdraw, 2: Deposit, 3: Check Balance, 4: Exit):");
            int number = sc.nextInt();

            if (number == 4) {
                System.out.println("Thank you!");
                break;
            }

            int amount = 0;
            if (number == 1 || number == 2) {
                System.out.println("Enter amount:");
                amount = sc.nextInt();
            }

            switch (number) {
                case 1:
                    if (Balance < amount) {
                        System.out.println("You haven't sufficient balance.");
                    } else {
                        withdraw(amount);
                    }
                    break;
                case 2:
                    deposite(amount);
                    break;
                case 3:
                    checkBalance();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

public class Demo1 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.performOperations();
    }
}
