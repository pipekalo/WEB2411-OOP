package edu.chdtu.web2411.poliakov.lab3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String email;

    private List<Task> assignedTasks;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.assignedTasks = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void addAssignedTask(Task mewTask) {
        assignedTasks.add(mewTask);
    }

    public List<Task> getAssignedTasks() {
        return this.assignedTasks;
    }
}
