package edu.chdtu.web2411.poliakov.lab1.accounts;

import edu.chdtu.web2411.poliakov.lab1.enums.AccountStatus;
import edu.chdtu.web2411.poliakov.lab1.enums.AccountType;
import edu.chdtu.web2411.poliakov.lab1.interfaces.Predicate;
import edu.chdtu.web2411.poliakov.lab1.interfaces.operations.BankOperations;


public abstract class Account {
    private BankOperations bankOperations;
    protected String owner;
    protected String cardNumber;
    protected double balance;

    protected AccountStatus status;
    protected AccountType accountType;

    public Account(String owner, String cardNumber, double balance) {
        this.owner = owner;
        this.cardNumber = cardNumber;
        this.balance = balance;
    }


    public boolean withdraw(double amount) {
        Predicate<Double> bankWithdraw = amt -> {
            if(this.balance - amt < 0 && amt < this.balance) {
                this.balance -= amt;
                return true;
            }
            return false;
        };
        return  bankWithdraw.execute(amount);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
