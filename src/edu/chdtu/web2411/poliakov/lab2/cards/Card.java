package edu.chdtu.web2411.poliakov.lab2.cards;

public class Card {
    protected String owner;
    protected String cardNumber;
    protected double balance;

    public Card(double balance, String cardNumber, String owner) {
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.owner = owner;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
