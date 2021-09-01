package objectorienteddesign.Bank.src;

public interface Bank {
    public void deposit(int accountNumber, double amount) throws InvalidAmountException;

    public void withdraw(int accountNumber, double amount) throws InsufficientFundsException;

    public void balance(int accountNumber);

    public String toString(int accountNumber);

    public int createAccount(int PAN, String firstName, String lastName, String city);
}
