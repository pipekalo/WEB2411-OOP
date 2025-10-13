package edu.chdtu.web2411.poliakov.lab2.accounts.personal;

import edu.chdtu.web2411.poliakov.lab2.accounts.Account;
import edu.chdtu.web2411.poliakov.lab2.enums.AccountType;

public class PersonalAccount extends Account {
    protected double limitPerDay;
    protected double interestRate = 10;

    public PersonalAccount(String owner, String cardNumber, double balance, double limitPerDay) {
        super(owner, cardNumber, balance);
        this.limitPerDay = limitPerDay;
        this.accountType = AccountType.USER;
    }

    public double setLimitPerDay(double limit) {
        this.limitPerDay = limit;
        return this.limitPerDay;
    }

    @Override
    public boolean withdraw(double amount) {
        boolean result = super.withdraw(amount);
        if ((this.card.getBalance() - amount) <= this.limitPerDay && result) {
            this.limitPerDay -= amount;
            return true;
        }
        return false;
    }

    public double calculateCashback(double amountSpent, double cashbackPercent) {
        double balance = this.card.getBalance();
        if (balance < amountSpent) return 0;
        this.card.setBalance(balance - amountSpent);
        double cashback = (amountSpent / 100) * cashbackPercent;
        this.card.setBalance(balance + cashback);
        return balance;
    }

}
