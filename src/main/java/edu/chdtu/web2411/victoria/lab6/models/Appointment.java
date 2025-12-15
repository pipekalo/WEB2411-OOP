package edu.chdtu.web2411.victoria.lab6.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Appointment implements Serializable {
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime dateTime;
    private String notes;

    public Appointment(Doctor doctor, Patient patient, String notes) {
        this.doctor = doctor;
        this.patient = patient;
        this.dateTime = LocalDateTime.now();
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Date: " + dateTime.toString() +
                " | Doctor: " + doctor.getName() +
                " | Patient: " + patient.getName() +
                " | Note: " + notes;
    }
}