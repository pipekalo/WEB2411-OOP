package edu.chdtu.web2411.poliakov.lab1.interfaces.operations;

import edu.chdtu.web2411.poliakov.lab1.accounts.Account;

public class BankOperations {
    boolean payToCard(double amount, Account fromAccount, Account toAccount) {
        if(fromAccount.withdraw(amount)) {
            toAccount.setBalance(toAccount.getBalance() + amount);
        }
        return false;
    }
}
