package edu.chdtu.web2411.victoria.lab1.models;

public class CorporateClient extends Client {
    private String taxId;

    public CorporateClient(int id, String name, double balance, String taxId) {
        super(id, name, balance);
        this.taxId = taxId;
    }

    @Override
    public String getContactInfo() {
        return "Tax ID: " + taxId;
    }

    @Override
    public void displayDetails() {
        System.out.println("Corporate Client: " + name + ", Balance: " + balance + ", " + getContactInfo());
    }
}