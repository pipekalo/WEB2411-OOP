package edu.chdtu.web2411.victoria.lab5.visitors;

import edu.chdtu.web2411.victoria.lab5.interfaces.IVisitor;
import edu.chdtu.web2411.victoria.lab5.models.Doctor;
import edu.chdtu.web2411.victoria.lab5.models.Patient;

public class ConsoleReportVisitor implements IVisitor {

  @Override
  public void visit(Doctor doctor) {
    System.out.println("------------------------------");
    System.out.println("STAFF REPORT");
    System.out.println("ID: " + doctor.getId());
    System.out.println("Name: Dr. " + doctor.getName().toUpperCase());
    System.out.println("Spec: " + doctor.getSpecialization());
    System.out.println("------------------------------");
  }

  @Override
  public void visit(Patient patient) {
    System.out.println("******************************");
    System.out.println("PATIENT CARD");
    System.out.println("ID: " + patient.getId());
    System.out.println("Name: " + patient.getName());
    System.out.println("History: " + patient.getDiagnoses());
    System.out.println("******************************");
  }
}
