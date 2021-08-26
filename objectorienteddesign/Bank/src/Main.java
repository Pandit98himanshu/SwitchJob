import java.util.*;

public class Main {
    public static void main(String[] args) throws InvalidAmountException, InsufficientFundsException, InvalidBankException {
        Scanner sc = new Scanner(System.in);
        Bank account;

        System.out.println("Select Bank–\n" +
                "1.ICICI\t2.HDFC");
        int bankSelection = sc.nextInt();
        switch (bankSelection) {
            case 1 -> account = new ICICI();
            case 2 -> account = new HDFC();
            default -> throw new InvalidBankException("Not a valid bank.");
        }

        System.out.println("Select operation to be performed–\n" +
                "1.Deposit\n2.Withdraw\n3.View Balance\n4.Create Account");
        int operation = sc.nextInt();
        System.out.println("Enter account number–");
        int accountNumber = sc.nextInt();
        double amount;
        switch (operation) {
            case 1 -> {
                try {
                    System.out.println("Enter amount to be deposited–");
                    amount = sc.nextDouble();
                    account.deposit(accountNumber, amount);
                } catch (NullPointerException npe) {
                    System.out.println("Account does not exists.");
                }
            }
            case 2 -> {
                try {
                    System.out.println("Enter amount to be withdrawn–");
                    amount = sc.nextDouble();
                    account.withdraw(accountNumber, amount);
                } catch (NullPointerException npe) {
                    System.out.println("Account does not exists.");
                }
            }
            case 3 -> {
                try {
                    account.balance(accountNumber);
                } catch (NullPointerException npe) {
                    System.out.println("Account does not exists.");
                }
            }
            case 4 -> {
                System.out.println("Enter your PAN number-");
                int PAN = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter your first name-");
                String firstName = sc.nextLine();
                System.out.println("Enter your last name-");
                String lastName = sc.nextLine();
                System.out.println("Enter your city-");
                String city = sc.nextLine();
                accountNumber = account.createAccount(PAN, firstName, lastName, city);
            }
        }

        try {
            System.out.println(account.toString(accountNumber));
        } catch (NullPointerException npe) {
            System.out.println("Account does not exists.");
        }
    }
}
