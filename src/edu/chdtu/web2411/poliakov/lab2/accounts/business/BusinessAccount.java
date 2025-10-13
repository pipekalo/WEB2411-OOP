package edu.chdtu.web2411.poliakov.lab2.accounts.business;

import edu.chdtu.web2411.poliakov.lab2.accounts.Account;
import edu.chdtu.web2411.poliakov.lab2.enums.AccountType;
import edu.chdtu.web2411.poliakov.lab2.interfaces.Function;


public class BusinessAccount extends Account {
    protected String companyName;
    protected double creditLimit;
    protected double interestRate;

    public BusinessAccount(String owner, String cardNumber, double balance, String companyName, double creditLimit, double interestRate) {
        super(owner, cardNumber, balance);
        this.companyName = companyName;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.accountType = AccountType.BUSINESS;
    }

    @Override
    public boolean withdraw(double amount) {
        boolean result = super.withdraw(amount);
        double available = this.card.getBalance() + this.creditLimit;
        return amount > 0 && amount <= available && result;
    }
}
