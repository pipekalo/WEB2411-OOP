package edu.chdtu.web2411.victoria.lab1.models;

public class IndividualClient extends Client {
    private String passportNumber;

    public IndividualClient(int id, String name, double balance, String passportNumber) {
        super(id, name, balance);
        this.passportNumber = passportNumber;
    }

    @Override
    public String getContactInfo() {
        return "Passport: " + passportNumber;
    }

    @Override
    public void displayDetails() {
        System.out.println("Individual Client: " + name + ", Balance: " + balance + ", " + getContactInfo());
    }
}