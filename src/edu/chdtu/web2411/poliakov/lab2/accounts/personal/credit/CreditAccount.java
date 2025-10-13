package edu.chdtu.web2411.poliakov.lab2.accounts.personal.credit;

import edu.chdtu.web2411.poliakov.lab2.accounts.personal.PersonalAccount;
import edu.chdtu.web2411.poliakov.lab2.enums.AccountType;

public class CreditAccount extends PersonalAccount {
    protected double creditLimit;
    protected double minimalPayment;
    protected double totalCredit;

    public CreditAccount(String owner, String cardNumber, double balance, double limitPerDay, double totalCredit) {
        super(owner, cardNumber, balance, limitPerDay);
        this.accountType = AccountType.CREDIT;
        this.totalCredit = totalCredit;
    }

    public void setCreditLimit(double newLimit) {
        this.creditLimit = newLimit;
    }

}
