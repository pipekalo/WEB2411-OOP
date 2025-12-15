package edu.chdtu.web2411.victoria.lab2.models;

public class IndividualClient extends Client {
    private String passportNumber;

    public IndividualClient(int id, String name, double balance, String city, String street, String passportNumber) {
        super(id, name, balance, city, street); 
        this.passportNumber = passportNumber;
    }

    @Override
    public String getContactInfo() {
        return "Passport: " + passportNumber;
    }

    @Override
    public void displayDetails() {
        // Додано виведення адреси
        System.out.println("Individual Client: " + name + 
                           ", Balance: " + balance + 
                           ", Address: " + address.toString() + 
                           ", " + getContactInfo());
    }
}