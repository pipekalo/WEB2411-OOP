package edu.chdtu.web2411.poliakov.lab1.accounts.personal.savings;

import edu.chdtu.web2411.poliakov.lab1.accounts.personal.PersonalAccount;
import edu.chdtu.web2411.poliakov.lab1.enums.AccountType;
import edu.chdtu.web2411.poliakov.lab1.interfaces.Predicate;

public class SavingsAccount extends PersonalAccount {
    protected double minimalBalance;
    public SavingsAccount(String owner, String cardNumber, double balance, double limitPerDay, double minimalBalance) {
        super(owner, cardNumber, balance, limitPerDay);
        this.minimalBalance = minimalBalance;
        this.accountType = AccountType.SAVINGS;
    }

    @Override
    public boolean withdraw(double amount) {
        Predicate<Double> savingsWithdraw = amt -> {
            if((amt > 0 && (this.balance - amt) >= this.minimalBalance)) {
                this.balance -= amt;
                return true;
            }
            return false;
        };
        return savingsWithdraw.execute(amount);
    }
}
