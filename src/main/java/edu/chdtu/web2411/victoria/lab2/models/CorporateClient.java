package edu.chdtu.web2411.victoria.lab2.models;

public class CorporateClient extends Client {
    private String taxId;

    public CorporateClient(int id, String name, double balance, String taxId, String city, String street) {
        super(id, name, balance, city, street);
        this.taxId = taxId;
    }

    @Override
    public String getContactInfo() {
        return "Tax ID: " + taxId;
    }

    @Override
    public void displayDetails() {
       System.out.println("Corporate Client: " + name + 
                           ", Balance: " + balance + 
                           ", Address: " + address.toString() + 
                           ", " + getContactInfo());
    }
    
}