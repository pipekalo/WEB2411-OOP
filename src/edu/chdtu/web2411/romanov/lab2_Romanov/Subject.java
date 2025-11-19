package edu.chdtu.web2411.romanov.lab2_Romanov;

public class Subject implements Gradable, SubmitTable {

    private String subjectName;
    private boolean submitted;
    private int grade;

    public Subject(String subjectName) {
        this.subjectName = subjectName;
        this.submitted = false;
        this.grade = 0;
    }

    public void submit() {
        this.submitted = true;
        System.out.println("Робота з '" + this.subjectName + "' здана.");
    }

    public boolean isSubmitted() { return this.submitted; }

    public void setGrade(int grade) {
        if (grade > 0 && grade <= 100) {
            this.grade = grade;
            System.out.println("Робота з '" + this.subjectName + "' оцінена на " + grade);
        } else {
            System.out.println("Неправильна оцінка: " + grade);
        }
    }

    public int getGrade() { return this.grade; }

    public boolean isGraded() { return this.grade > 0; }

    public String getSubjectName() { return subjectName; }
}
