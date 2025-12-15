package edu.chdtu.web2411.victoria.lab2.models;

import edu.chdtu.web2411.victoria.lab2.interfaces.IContactable;

public abstract class Client extends AbstractPerson implements IContactable {
    protected double balance;
    protected Address address;

    
    public Client(int id, String name, double balance, String city, String street) {
        super(id, name);
        this.balance = balance;
        // Реалізація КОМПОЗИЦІЇ: адреса створюється всередині
        this.address = new Address(city, street);
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }
}