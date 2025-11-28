package edu.chdtu.web2411.romanov.lab2_Romanov;

import java.util.ArrayList;
import java.util.List;

public class GradeBook {

    private List<Subject> subjects;

    public GradeBook() {
        subjects = new ArrayList<>();
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}
