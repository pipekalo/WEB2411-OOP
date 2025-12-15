package edu.chdtu.web2411.victoria.lab6.services;

import java.util.List;
import java.util.stream.Collectors;

import edu.chdtu.web2411.victoria.lab6.models.Appointment;
import edu.chdtu.web2411.victoria.lab6.models.Doctor;
import edu.chdtu.web2411.victoria.lab6.models.HospitalState;
import edu.chdtu.web2411.victoria.lab6.models.Patient;

public class HospitalService {
    private HospitalState state;

    public HospitalService(HospitalState state) {
        this.state = state;
    }

    // --- CRUD Лікарі ---
    public void addDoctor(int id, String name, String specialization) {
        state.doctors.add(new Doctor(id, name, specialization));
    }

    public List<Doctor> getAllDoctors() {
        return state.doctors;
    }

    // --- CRUD Пацієнти + Пошук ---
    public void addPatient(int id, String name) {
        state.patients.add(new Patient(id, name));
    }

    public List<Patient> searchPatients(String query) {
        return state.patients.stream()
                .filter(p -> p.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Patient getPatientById(int id) {
        return state.patients.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Doctor getDoctorById(int id) {
        return state.doctors.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    // --- Запис на прийом ---
    public void createAppointment(int docId, int patId, String diagnosis) {
        Doctor doc = getDoctorById(docId);
        Patient pat = getPatientById(patId);

        if (doc != null && pat != null) {
            // 1. Створюємо запис
            Appointment app = new Appointment(doc, pat, diagnosis);
            state.appointments.add(app);
            // 2. Оновлюємо історію хвороби пацієнта
            pat.addDiagnosis(diagnosis + " (Dr. " + doc.getName() + ")");
            System.out.println("Appointment created successfully!");
        } else {
            System.out.println("Error: Doctor or Patient not found.");
        }
    }

    // --- Формування Медичної Картки ---
    public void generateMedicalCard(int patientId) {
        Patient p = getPatientById(patientId);
        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("\n=================================");
        System.out.println("       MEDICAL CARD #" + p.getId());
        System.out.println("=================================");
        System.out.println("Patient: " + p.getName());
        System.out.println("--- MEDICAL HISTORY ---");

        List<String> history = p.getDiagnoses();
        if (history.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println((i + 1) + ". " + history.get(i));
            }
        }
        System.out.println("=================================\n");
    }

    // --- Пагінація ---
    public void printPatientsPage(int page, int pageSize) {
        List<Patient> list = state.patients;
        int total = list.size();
        int totalPages = (int) Math.ceil((double) total / pageSize);
        if (totalPages == 0)
            totalPages = 1;

        if (page < 1)
            page = 1;
        if (page > totalPages)
            page = totalPages;

        int from = (page - 1) * pageSize;
        int to = Math.min(from + pageSize, total);

        System.out.println("\n--- Patient List (Page " + page + "/" + totalPages + ") ---");
        for (int i = from; i < to; i++) {
            System.out.println(list.get(i));
        }
    }

    // --- Клонування Пацієнта ---
    public void clonePatient(int originalId, int newId) {
        Patient original = getPatientById(originalId);
        if (original == null) {
            System.out.println("Error: Original patient not found.");
            return;
        }

        Patient copy = original.clone();

        try {
            copy.setName(copy.getName() + " (Copy)");
            copy.setId(newId);

            state.patients.add(copy);
            System.out.println("Patient cloned successfully!");
            System.out.println("Original: " + original);
            System.out.println("Clone:    " + copy);
        } catch (Exception e) {
            System.out.println("Error during cloning customization.");
        }
    }

    public HospitalState getState() {
        return state;
    }
}