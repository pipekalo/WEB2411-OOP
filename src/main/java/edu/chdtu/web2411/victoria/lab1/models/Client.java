package edu.chdtu.web2411.victoria.lab1.models;

import edu.chdtu.web2411.victoria.lab1.interfaces.IContactable;

public abstract class Client extends AbstractPerson implements IContactable {
    protected double balance;

    public Client(int id, String name, double balance) {
        super(id, name);
        this.balance = balance;
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }
    
    
}