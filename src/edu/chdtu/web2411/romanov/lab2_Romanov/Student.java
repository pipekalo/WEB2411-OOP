package edu.chdtu.web2411.romanov.lab2_Romanov;

import java.util.List;

public class Student extends Person {

    private static int count = 1;

    private String studentID;
    private GradeBook gradeBook;

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
        count++;
        this.studentID = "Web-" + count;
    }

    public void setGradeBook(GradeBook bookFromDeanery) {
        this.gradeBook = bookFromDeanery;
    }

    public void addWork(Subject work) {
        if (gradeBook != null) {
            gradeBook.addSubject(work);
        } else {
            System.out.println("Помилка: У студента" + getLastName() + " немає залікової книжки");
        }
    }
    public List<Subject> getWorks() {
        if (gradeBook != null) {
            return gradeBook.getSubjects();
        }
        return null;
    }

    public void displayInfo() {
        System.out.println("СТУДЕНТ: " + getFirstName() + " " + getLastName() +
                " (ID: " + studentID + ")");
    }
}
