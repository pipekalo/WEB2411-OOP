package edu.chdtu.web2411.romanov.lab2_Romanov;

public class LaboratoryWork extends Subject {
    private int labNumber;

    public LaboratoryWork(String subjectName, int labNumber) {
        super(subjectName);
        this.labNumber = labNumber;
    }

    public void showInfo() {
        System.out.println("Лаба №" + labNumber + " з '" + getSubjectName() + "'");
    }
}
