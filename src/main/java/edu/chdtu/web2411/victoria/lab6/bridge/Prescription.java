package edu.chdtu.web2411.victoria.lab6.bridge;

import edu.chdtu.web2411.victoria.lab6.models.Person;

public class Prescription extends MedicalDocument {
  private String medicine;
  private String dosage;

  public Prescription(IDocumentRenderer renderer, Person patient, String medicine, String dosage) {
    super(renderer, patient);
    this.medicine = medicine;
    this.dosage = dosage;
  }

  @Override
  public void printDocument() {
    System.out.print(renderer.renderHeader("Medical Prescription"));

    String body = "Patient: " + patient.getName() + "\n" +
        "Drug: " + medicine + "\n" +
        "Dosage: " + dosage;

    System.out.print(renderer.renderBody(body));
    System.out.print(renderer.renderFooter("Doctor Signature: ___________"));
  }
}