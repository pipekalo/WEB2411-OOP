package edu.chdtu.web2411.romanov.Lab1_Romanov;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    public static int count = 0;
    public String studentID;
    public List<Subject> works;

    public Student(String firstName, String lastName) {
        super(firstName, lastName);

        count++;

        this.studentID = "Web-" + count;

        this.works = new ArrayList<>();
    }
    public void addWork(Subject work) {
        works.add(work);
    }

    public List<Subject> getWorks() {
        return works;
    }

    @Override
    public void displayInfo() {
        System.out.println("Студент: " + firstName + " " + lastName + " (ID: " + studentID + ")");
    }
}