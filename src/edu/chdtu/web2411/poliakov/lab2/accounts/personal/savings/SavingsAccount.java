package edu.chdtu.web2411.poliakov.lab2.accounts.personal.savings;

import edu.chdtu.web2411.poliakov.lab2.accounts.personal.PersonalAccount;
import edu.chdtu.web2411.poliakov.lab2.enums.AccountType;
import edu.chdtu.web2411.poliakov.lab2.interfaces.Predicate;

public class SavingsAccount extends PersonalAccount {
    protected double minimalBalance;

    public SavingsAccount(String owner, String cardNumber, double balance, double limitPerDay, double minimalBalance) {
        super(owner, cardNumber, balance, limitPerDay);
        this.minimalBalance = minimalBalance;
        this.accountType = AccountType.SAVINGS;
    }

    @Override
    public boolean withdraw(double amount) {
        boolean isWithdraw = super.withdraw(amount);
        if ((this.card.getBalance() - amount) >= this.minimalBalance) {
            return isWithdraw;
        }
        return false;
    }
}
