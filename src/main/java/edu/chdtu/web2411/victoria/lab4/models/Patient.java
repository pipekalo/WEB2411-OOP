package edu.chdtu.web2411.victoria.lab4.models;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private List<String> diagnoses;

    public Patient(int id, String name) {
        super(id, name);
        this.diagnoses = new ArrayList<>();
    }

    // Конструктор копіювання з глибоким копіюванням списку
    private Patient(Patient target) {
        super(target.id, target.name);
        // Створюємо новий список, копіюючи туди дані зі старого
        if (target.diagnoses != null) {
            this.diagnoses = new ArrayList<>(target.diagnoses);
        } else {
            this.diagnoses = new ArrayList<>();
        }
    }

    @Override
    public Patient clone() {
        return new Patient(this);
    }

    public void addDiagnosis(String diagnosis) {
        diagnoses.add(diagnosis);
    }

    public List<String> getDiagnoses() {
        return diagnoses;
    }

    @Override
    public String toString() {
        return super.toString() + " (History: " + diagnoses.size() + " records)";
    }
}
