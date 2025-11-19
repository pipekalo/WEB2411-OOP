package edu.chdtu.web2411.romanov.lab2_Romanov;

import java.util.ArrayList;
import java.util.List;

public class StudentGroup {

    private String groupName;
    private List<Student> students;
    private GroupLeader groupLeader;

    public StudentGroup(String groupName) {
        this.groupName = groupName;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student); }
    public void setGroupLeader(GroupLeader leader) {
        this.groupLeader = leader; }

    public void displayGroupInfo() {
        System.out.println("Група: " + groupName);

        if (groupLeader != null) {
            System.out.println("Староста: " + groupLeader.getFirstName() +
                    " " + groupLeader.getLastName());
        } else {
            System.out.println("Старосту не призначено");
        }

        System.out.println("Склад групи (" + students.size() + " осіб):");
        for (Student s : students) s.displayInfo();
    }

    public double calculateAverageGrade() {
        double totalSum = 0;
        int totalGradesCount = 0;

        for (Student student : this.students) {
            for (Subject work : student.getWorks()) {
                if (work.isGraded()) {
                    totalSum = totalSum + work.getGrade();
                    totalGradesCount = totalGradesCount + 1;
                }
            }
        }

        if (totalGradesCount == 0) {
            return 0.0;
        }

        return totalSum / totalGradesCount;
    }
}


