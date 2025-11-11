package edu.chdtu.web2411.poliakov.lab3;

import edu.chdtu.web2411.poliakov.lab3.services.TaskService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private int totalTask;
    private List<Task> taskList;


    public Project(int id, String title, List<Task> taskList) {
        this.id = id;
        this.title = title;
        this.taskList = taskList;
        this.totalTask = this.taskList.size();
    }

    public int getId() {
        return this.id;
    }

    public List<Task> getTaskList() {
        return this.taskList == null ? new ArrayList<>() : this.taskList;
    }

    public void setTaskList(List<Task> newTaskList) {
        this.totalTask = newTaskList.size();
    }

    public String getInfo() {
        return "ID: " + this.id + ", Title: " + this.title + ", Total tasks: " + this.totalTask;
    }
}
