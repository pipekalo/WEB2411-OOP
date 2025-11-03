package edu.chdtu.web2411.poliakov.lab3;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int id;
    private String title;
    private int totalTask;
    private List<Task> taskList;

    public Project(int id, String title) {
        this.id = id;
        this.title = title;
        this.taskList = new ArrayList<>();
        this.totalTask = taskList.size();
    }

    public int getId() {
        return this.id;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void setTaskList(List<Task> newTaskList) {
        this.taskList = newTaskList;
        this.totalTask = taskList.size();
    }

    public String getInfo() {
        return "ID: " + this.id + ", Title: " + this.title + ", Total tasks: " + this.totalTask;
    }
}
