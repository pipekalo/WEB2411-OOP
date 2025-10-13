package edu.chdtu.web2411.poliakov.lab2.accounts;

import edu.chdtu.web2411.poliakov.lab2.cards.Card;
import edu.chdtu.web2411.poliakov.lab2.enums.AccountStatus;
import edu.chdtu.web2411.poliakov.lab2.enums.AccountType;
import edu.chdtu.web2411.poliakov.lab2.interfaces.Function;


public abstract class Account {
    protected Card card;
    protected AccountStatus status;
    protected AccountType accountType;

    public Account(String owner, String cardNumber, double balance) {
        this.card = new Card(balance, cardNumber, owner);
    }

    public boolean withdraw(double amount) {
        double balance = this.card.getBalance();
        if (balance - amount < 0 && amount < balance) {
            this.card.setBalance(balance - amount);
            return true;
        }
        return false;
    }

    public Double calculateInterest(Integer days, double interestRate) {
        double balance = this.card.getBalance();
        Function<Integer, Double> calculate = value -> {
            if (balance >= 0) return 0.0;
            double overdraft = -balance;
            return Math.floor((overdraft * interestRate * value) / (365 * 100));
        };
        return calculate.execute(days);
    }
}
