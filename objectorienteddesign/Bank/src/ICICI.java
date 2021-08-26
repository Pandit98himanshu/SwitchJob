public class ICICI implements Bank {
    private final String bankName = "ICICI";
    private static final int minimumBalance = 500;
    BankDAOImpl connectToData = new BankDAOImpl();

    @Override
    public void deposit(int accountNumber, double amount) throws InvalidAmountException {
        Account account = connectToData.getAccountDetails(accountNumber, bankName);
        double balance = connectToData.getBalance(account);
        double creditedAmount = balance + amount;
        if (amount < 1d)
            throw new InvalidAmountException("Minimum deposit amount is ₹1.");
        connectToData.updateBalance(account, creditedAmount);
    }

    @Override
    public void withdraw(int accountNumber, double amount) throws InsufficientFundsException {
        Account account = connectToData.getAccountDetails(accountNumber, bankName);
        double balance = connectToData.getBalance(account);
        double debitedAmount = balance - amount;
        if (debitedAmount < minimumBalance)
            throw new InsufficientFundsException("Insufficient fund in your account.");
        connectToData.updateBalance(account, debitedAmount);
    }

    @Override
    public void balance(int accountNumber) {
        Account account = connectToData.getAccountDetails(accountNumber, bankName);
        System.out.println(connectToData.getBalance(account));
    }

    @Override
    public int createAccount(int PAN, String firstName, String lastName, String city) {
        return connectToData.createAccount(PAN, firstName, lastName, city, bankName);
    }

    @Override
    public String toString(int accountNumber) {
        Account account = connectToData.getAccountDetails(accountNumber, bankName);
        return "HDFC{" +
                "bankName='" + bankName + '\'' +
                ", accountNumber='" + account.getAccountNumber() +  '\'' +
                ", balance='" + account.getBalance() +  '\'' +
                ", accountHolderName='" + account.getAccountHolderName() + '\'' +
                '}';
    }
}
