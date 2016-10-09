package concurrency;

public class Account {
    int    userNumber;
    String userLastName;
    String userFirstName;
    double userBalance;

    public boolean deposit(double amount) {
        double newBalance;
        if (amount < 0.0) {
            return false; /* Can’t deposit negative amount */
        } else {
            newBalance = userBalance + amount;
            userBalance = newBalance;
            return true;
        }
    }

    public boolean withdraw(double amount) {
        double newBalance;
        if (amount < 0.0 || amount > userBalance) {
            return false; /* Negative withdrawal or insufficient funds */
        } else {
            newBalance = userBalance - amount;
            userBalance = newBalance;
            return true;
        }
    }
}