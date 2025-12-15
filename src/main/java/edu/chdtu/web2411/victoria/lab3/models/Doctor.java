package edu.chdtu.web2411.victoria.lab3.models;

public class Doctor extends Person {
    private String specialization;

    public Doctor(int id, String name, String specialization) {
        super(id, name);
        this.specialization = specialization;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - " + specialization;
    }
}
