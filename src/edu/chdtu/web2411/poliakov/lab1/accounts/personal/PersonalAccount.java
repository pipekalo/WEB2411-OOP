package edu.chdtu.web2411.poliakov.lab1.accounts.personal;

import edu.chdtu.web2411.poliakov.lab1.accounts.Account;
import edu.chdtu.web2411.poliakov.lab1.enums.AccountType;
import edu.chdtu.web2411.poliakov.lab1.interfaces.Function;
import edu.chdtu.web2411.poliakov.lab1.interfaces.Predicate;

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
        Predicate<Double> personalWithdraw = amt -> {
            if ((this.balance - amt) <= this.limitPerDay && (this.balance - amt > 0)) {
                this.limitPerDay -= amt;
                this.balance -= amt;
                return true;
            }
            return false;
        };
        return personalWithdraw.execute(amount);
    }

    public double calculateCashback(double amountSpent, double cashbackPercent) {
        if (this.balance < amountSpent) return 0;
        this.balance -= amountSpent;
        double cashback = (amountSpent / 100) * cashbackPercent;
        return this.balance += cashback;
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
