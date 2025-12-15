package edu.chdtu.web2411.victoria.lab6.models;

import edu.chdtu.web2411.victoria.lab6.interfaces.IVisitor;

public class Doctor extends Person {
    private String specialization;

    public Doctor(int id, String name, String specialization) {
        super(id, name);
        this.specialization = specialization;
    }

    // Конструктор для клонування
    private Doctor(Doctor target) {
        super(target.id, target.name);
        this.specialization = target.specialization;
    }

    @Override
    public Doctor clone() {
        return new Doctor(this);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + specialization;
    }
}