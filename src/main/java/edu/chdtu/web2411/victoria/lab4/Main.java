package edu.chdtu.web2411.victoria.lab4;

import java.util.Scanner;

import edu.chdtu.web2411.victoria.lab4.models.HospitalState;
import edu.chdtu.web2411.victoria.lab4.services.HospitalService;
import edu.chdtu.web2411.victoria.lab4.utils.DataManager;

public class Main {
    public static void main(String[] args) {
        // 1. Завантаження даних
        HospitalState state = DataManager.loadState();
        HospitalService service = new HospitalService(state);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("\n=== HOSPITAL SYSTEM ===");
                System.out.println("1. [Patients] List by Page");
                System.out.println("2. [Patients] Search by Name");
                System.out.println("3. [Patients] Add New");
                System.out.println("4. [Doctors] List All");
                System.out.println("5. [Doctors] Add New");
                System.out.println("6. [Process] Make Appointment");
                System.out.println("7. [Process] Print Medical Card");
                System.out.println("8. [Process] Clone Patient");
                System.out.println("0. Save & Exit");
                System.out.print(">>> ");

                String choice = scanner.next();
                scanner.nextLine(); // fix scanner bug

                switch (choice) {
                    case "1":
                        // Проста пагінація
                        System.out.print("Enter page number: ");
                        int page = scanner.nextInt();
                        service.printPatientsPage(page, 3);
                        break;
                    case "2":
                        System.out.print("Enter name to search: ");
                        String query = scanner.nextLine();
                        System.out.println("Found: " + service.searchPatients(query));
                        break;
                    case "3":
                        System.out.print("ID: ");
                        int pId = scanner.nextInt();
                        if (service.getPatientById(pId) != null) {
                            System.out.println("ID already exists.");
                            break;
                        }
                        System.out.print("Name: ");
                        String pName = scanner.next();
                        service.addPatient(pId, pName);
                        break;
                    case "4":
                        System.out.println(service.getAllDoctors());
                        break;
                    case "5":
                        System.out.print("ID: ");
                        int dId = scanner.nextInt();
                        if (service.getDoctorById(dId) != null) {
                            System.out.println("ID already exists.");
                            break;
                        }
                        System.out.print("Name: ");
                        String dName = scanner.next();
                        System.out.print("Specialization: ");
                        String spec = scanner.next();
                        service.addDoctor(dId, dName, spec);
                        break;
                    case "6":
                        System.out.print("Doctor ID: ");
                        int docId = scanner.nextInt();
                        System.out.print("Patient ID: ");
                        int patId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Diagnosis/Notes: ");
                        String diag = scanner.nextLine();
                        service.createAppointment(docId, patId, diag);
                        break;
                    case "7":
                        System.out.print("Enter Patient ID for Card: ");
                        int cardId = scanner.nextInt();
                        service.generateMedicalCard(cardId);
                        break;
                    case "8":
                        System.out.println("--- Clone Patient ---");
                        System.out.print("Enter Original Patient ID: ");
                        int orgId = scanner.nextInt();
                        System.out.print("Enter New ID for Clone: ");
                        int newId = scanner.nextInt();

                        service.clonePatient(orgId, newId);
                        break;
                    case "0":
                        DataManager.saveState(service.getState());
                        running = false;
                        break;
                }
            }
        }
    }
}