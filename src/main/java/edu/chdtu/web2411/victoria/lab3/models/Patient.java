package edu.chdtu.web2411.victoria.lab3.models;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private List<String> diagnoses; // Історія хвороби

    public Patient(int id, String name) {
        super(id, name);
        this.diagnoses = new ArrayList<>();
    }

    public void addDiagnosis(String diagnosis) {
        diagnoses.add(diagnosis);
    }

    public List<String> getDiagnoses() { return diagnoses; }
}
