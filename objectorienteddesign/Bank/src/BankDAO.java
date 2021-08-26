public interface BankDAO {
    public Account getAccountDetails(int accountNumber, String bankName);

    public void updateBalance(Account account, double amount) throws InvalidAmountException;

    public double getBalance(Account account);

    public int createAccount(int PAN, String firstName, String lastName, String city, String BankName);
}
