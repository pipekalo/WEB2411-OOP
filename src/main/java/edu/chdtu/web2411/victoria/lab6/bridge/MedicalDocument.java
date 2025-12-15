package edu.chdtu.web2411.victoria.lab6.bridge;

import edu.chdtu.web2411.victoria.lab6.models.Person;

public abstract class MedicalDocument {
  protected IDocumentRenderer renderer;
  protected Person patient;

  public MedicalDocument(IDocumentRenderer renderer, Person patient) {
    this.renderer = renderer;
    this.patient = patient;
  }

  public void setRenderer(IDocumentRenderer renderer) {
    this.renderer = renderer;
  }

  public abstract void printDocument();
}