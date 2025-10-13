package edu.chdtu.web2411.poliakov.lab3.model;

import edu.chdtu.web2411.poliakov.lab3.enums.TaskType;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String description;
    private TaskType taskType;
    private LocalDate createdTask;
    private LocalDate deadline;
    private int priority;


    public Task(int id, String title, String description, int daysToDeadline, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.taskType = TaskType.TODO;
        this.createdTask = LocalDate.now();
        this.deadline = LocalDate.now().plusDays(daysToDeadline);
        this.priority = Math.max(1, Math.min(5, priority));
    }

    public int getId() {
        return this.id;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setDeadline(int deadline) {
        this.deadline = LocalDate.now().plusDays(deadline);
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getInfo() {
        return "ID: " + this.id + ", Title: " + this.title + ", Description: " + this.description + ", Status: " + this.taskType + ", Priority: " + this.priority + ", CreateAt: " + this.createdTask + ", Deadline: " + this.deadline;
    }
}
