package edu.chdtu.web2411.victoria.lab5.visitors;

import edu.chdtu.web2411.victoria.lab5.interfaces.IVisitor;
import edu.chdtu.web2411.victoria.lab5.models.Doctor;
import edu.chdtu.web2411.victoria.lab5.models.Patient;

public class CSVExportVisitor implements IVisitor {
  private StringBuilder csvBuffer = new StringBuilder();

  public CSVExportVisitor() {
    csvBuffer.append("Type,ID,Name,Details\n");
  }

  @Override
  public void visit(Doctor doctor) {
    csvBuffer.append(String.format("DOCTOR,%d,%s,%s\n",
        doctor.getId(), doctor.getName(), doctor.getSpecialization()));
  }

  @Override
  public void visit(Patient patient) {
    String history = String.join(";", patient.getDiagnoses());
    csvBuffer.append(String.format("PATIENT,%d,%s,[%s]\n",
        patient.getId(), patient.getName(), history));
  }

  public String getCSV() {
    return csvBuffer.toString();
  }
}
