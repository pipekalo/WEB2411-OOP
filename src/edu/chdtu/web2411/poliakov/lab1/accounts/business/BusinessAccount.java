package edu.chdtu.web2411.poliakov.lab1.accounts.business;

import edu.chdtu.web2411.poliakov.lab1.Account;
import edu.chdtu.web2411.poliakov.lab1.enums.AccountType;
import edu.chdtu.web2411.poliakov.lab1.interfaces.Function;
import edu.chdtu.web2411.poliakov.lab1.interfaces.Predicate;


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
        Predicate<Double> businessWithdraw = amt -> {
            double available = this.balance + this.creditLimit;
            if(amt > 0 && amt <= available) {
                this.balance -= amt;
                return true;
            }
            return false;
        };
        return businessWithdraw.execute(amount);
    }

    public Double calculateInterest(Integer days) {
        Function<Integer, Double> calculate = value -> {
            if (balance >= 0) return 0.0;
            double overdraft = -balance;
            return Math.floor((overdraft * this.interestRate * value) / (365 * 100));
        };
        return calculate.execute(days);
    }
}
