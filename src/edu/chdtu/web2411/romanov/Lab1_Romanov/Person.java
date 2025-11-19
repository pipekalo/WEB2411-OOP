package edu.chdtu.web2411.romanov.Lab1_Romanov;

public class Person {
    public String firstName;
    public String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void displayInfo() {
        System.out.println("Людина: " + firstName + " " + lastName);
    }
}
