package edu.chdtu.web2411.victoria.lab6.interfaces;

import edu.chdtu.web2411.victoria.lab6.models.Doctor;
import edu.chdtu.web2411.victoria.lab6.models.Patient;

public interface IVisitor {
  void visit(Doctor doctor);

  void visit(Patient patient);
}
