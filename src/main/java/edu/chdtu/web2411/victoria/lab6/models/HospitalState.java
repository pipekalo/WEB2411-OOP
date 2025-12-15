package edu.chdtu.web2411.victoria.lab6.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HospitalState implements Serializable {

    public List<Doctor> doctors = new ArrayList<>();
    public List<Patient> patients = new ArrayList<>();
    public List<Appointment> appointments = new ArrayList<>();
}