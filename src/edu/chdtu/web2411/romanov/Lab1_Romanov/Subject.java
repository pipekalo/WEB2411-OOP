package edu.chdtu.web2411.romanov.Lab1_Romanov;

public class Subject implements Gradable, SubmitTable {
    public String subjectName;
    public boolean submitted;
    public int grade;

    public Subject(String subjectName) {
        this.subjectName = subjectName;
        this.submitted = false;
        this.grade = 0;
    }

    @Override
    public void submit() {
        submitted = true;
        System.out.println("Робота з '" + subjectName + "' здана.");
    }

    @Override
    public boolean isSubmitted() { return submitted; }

    @Override
    public void setGrade(int grade) {
        if (grade > 0 && grade <= 100) this.grade = grade;
    }

    @Override
    public int getGrade() { return grade; }

    @Override
    public boolean isGraded() { return grade > 0; }
}