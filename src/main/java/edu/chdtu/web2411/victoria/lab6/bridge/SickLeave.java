package edu.chdtu.web2411.victoria.lab6.bridge;

import edu.chdtu.web2411.victoria.lab6.models.Person;

public class SickLeave extends MedicalDocument {
  private int days;
  private String diagnosis;

  public SickLeave(IDocumentRenderer renderer, Person patient, String diagnosis, int days) {
    super(renderer, patient);
    this.diagnosis = diagnosis;
    this.days = days;
  }

  @Override
  public void printDocument() {
    System.out.print(renderer.renderHeader("Sick Leave Certificate"));

    String body = "This certifies that " + patient.getName() + " is unable to work.\n" +
        "Reason: " + diagnosis + "\n" +
        "Duration: " + days + " days.";

    System.out.print(renderer.renderBody(body));
    System.out.print(renderer.renderFooter("Official Hospital Seal"));
  }
}