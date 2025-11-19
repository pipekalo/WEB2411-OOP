package edu.chdtu.web2411.romanov.lab2_Romanov;

public class CourseWork extends Subject {
    private String topic;

    public CourseWork(String subjectName, String topic) {
        super(subjectName);
        this.topic = topic;
    }

    public void showInfo() {
        System.out.println("-> Курсова з '" + getSubjectName() + "' на тему '" + topic + "'");
    }
}
