import java.util.HashMap;

public class BankDAOImpl implements BankDAO {
    private static int accountNumberICICI = 1;
    private static int accountNumberHDFC = 1;
    private static HashMap<Integer, Account> accountsICICI = new HashMap<>();
    private static HashMap<Integer, Account> accountsHDFC = new HashMap<>();

    @Override
    public Account getAccountDetails(int accountNumber, String bankName) {
        return switch (bankName) {
            case "ICICI" -> accountsICICI.get(accountNumber);
            case "HDFC" -> accountsHDFC.get(accountNumber);
            default -> null;
        };
    }

    @Override
    public double getBalance(Account account) {
        int accountNumber = account.getAccountNumber();
        Account bankAccount;
        String bankName = account.getBankName();
        switch (bankName) {
            case "ICICI":
                bankAccount = accountsICICI.get(accountNumber);
                return bankAccount.getBalance();
            case "HDFC":
                bankAccount = accountsHDFC.get(accountNumber);
                return bankAccount.getBalance();
            default:
                return 0d;
        }
    }

    @Override
    public void updateBalance(Account account, double amount) {
        int accountNumber = account.getAccountNumber();
        Account bankAccount;
        String bankName = account.getBankName();
        switch (bankName) {
            case "ICICI":
                bankAccount = accountsICICI.get(accountNumber);
                bankAccount.setBalance(amount);
                accountsICICI.put(accountNumber, bankAccount);
                break;
            case "HDFC":
                bankAccount = accountsHDFC.get(accountNumber);
                bankAccount.setBalance(amount);
                accountsHDFC.put(accountNumber, bankAccount);
                break;
        }
    }

    @Override
    public int createAccount(int PAN, String firstName, String lastName, String city, String bankName) {
        Account newAccount;
        switch (bankName) {
            case "ICICI":
                newAccount = new Account(++accountNumberICICI, 0, firstName + " " + lastName, bankName);
                accountsICICI.put(accountNumberICICI, newAccount);
                return accountNumberICICI;
            case "HDFC":
                newAccount = new Account(++accountNumberHDFC, 0, firstName + " " + lastName, bankName);
                accountsHDFC.put(accountNumberHDFC, newAccount);
                return accountNumberHDFC;
        }
        return -1;
    }
}
