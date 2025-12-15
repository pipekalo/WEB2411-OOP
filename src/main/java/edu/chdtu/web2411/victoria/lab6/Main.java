package edu.chdtu.web2411.victoria.lab6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.chdtu.web2411.victoria.lab6.bridge.HtmlRenderer;
import edu.chdtu.web2411.victoria.lab6.bridge.IDocumentRenderer;
import edu.chdtu.web2411.victoria.lab6.bridge.MedicalDocument;
import edu.chdtu.web2411.victoria.lab6.bridge.Prescription;
import edu.chdtu.web2411.victoria.lab6.bridge.SickLeave;
import edu.chdtu.web2411.victoria.lab6.bridge.SimpleTextRenderer;
import edu.chdtu.web2411.victoria.lab6.models.HospitalState;
import edu.chdtu.web2411.victoria.lab6.models.Person;
import edu.chdtu.web2411.victoria.lab6.services.HospitalService;
import edu.chdtu.web2411.victoria.lab6.utils.DataManager;
import edu.chdtu.web2411.victoria.lab6.visitors.CSVExportVisitor;
import edu.chdtu.web2411.victoria.lab6.visitors.ConsoleReportVisitor;

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
                System.out.println("9. [Process] Export Data");
                System.out.println("10. [Process] Generate Reports");
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
                    case "9":
                        System.out.println("Choose Visitor: [1] Console Report, [2] CSV Export");
                        String vChoice = scanner.next();

                        List<Person> people = new ArrayList<>();
                        people.addAll(service.getAllDoctors());
                        people.addAll(service.getState().patients);

                        if (vChoice.equals("1")) {
                            System.out.println("\n--- Visitor: Console Report ---");
                            ConsoleReportVisitor consoleVisitor = new ConsoleReportVisitor();
                            for (Person p : people) {
                                p.accept(consoleVisitor);
                            }
                        } else if (vChoice.equals("2")) {
                            System.out.println("\n--- Visitor: CSV Export ---");
                            CSVExportVisitor csvVisitor = new CSVExportVisitor();
                            for (Person p : people) {
                                p.accept(csvVisitor);
                            }
                            System.out.println("RESULT CSV:\n" + csvVisitor.getCSV());
                        }
                        break;
                    case "10":
                        System.out.println("Enter Patient ID for document generation: ");
                        int patientId = scanner.nextInt();
                        Person patient = service.getPatientById(patientId);

                        if (patient == null) {
                            System.out.println("Patient not found!");
                            break;
                        }

                        // Ініціалізація реалізацій
                        IDocumentRenderer textRenderer = new SimpleTextRenderer();
                        IDocumentRenderer htmlRenderer = new HtmlRenderer();

                        // Створення абстракції (Рецепт) з текстовим рендерером
                        System.out.println("\n--- 1. Generating Prescription (TEXT Mode) ---");
                        MedicalDocument prescription = new Prescription(textRenderer, patient, "Paracetamol",
                                "1 tablet per day for 5 days");
                        prescription.printDocument();

                        // Динамічна зміна реалізації через Bridge
                        System.out.println("\n--- 2. Switching to HTML Mode (Same Object) ---");
                        prescription.setRenderer(htmlRenderer);
                        prescription.printDocument();

                        // 4. Інший тип документа (Лікарняний)
                        System.out.println("\n--- 3. Generating Sick Leave (HTML Mode) ---");
                        MedicalDocument sickLeave = new SickLeave(htmlRenderer, patient, "Flu", 7);
                        sickLeave.printDocument();
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