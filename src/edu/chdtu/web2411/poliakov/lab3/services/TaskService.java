package edu.chdtu.web2411.poliakov.lab3.services;

import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.Task;
import edu.chdtu.web2411.poliakov.lab3.enums.TaskType;
import edu.chdtu.web2411.poliakov.lab3.impls.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskService implements Service<Task> {
    private static TaskService instance;
    private List<Task> taskList;
    private ConsoleWriter consoleWriter;

    private static String DATA_FILE = "data/tasks.ser";

    private TaskService() {
        this.taskList = new ArrayList<>();
        this.consoleWriter = new ConsoleWriter();
        this.load();
    }

    public static TaskService getInstance() {
        if (instance == null) {
            instance = new TaskService();
        }
        return instance;
    }

    public boolean changeStatus(int id, TaskType newTaskType) {
        Task task = this.getAll().stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);

        if (task == null) {
            return false;
        }

        task.setTaskType(newTaskType);
        return true;
    }

    @Override
    public boolean add(Task item) {
        taskList.add(item);
        this.save();
        return true;
    }

    @Override
    public List<Task> getAll() {
        return new ArrayList<>(taskList);
    }

    @Override
    public Optional<Task> getById(int id) {
        return taskList.stream().filter(t -> t.getId() == id).findFirst();
    }

    @Override
    public boolean remove(int id) {
        boolean removedTask = taskList.removeIf(task -> task.getId() == id);
        if (removedTask) {
            this.save();
            return true;
        }
        return false;
    }

    @Override
    public boolean save() {
        File dir = new File( "data");
        if(!dir.exists()) {
            dir.mkdirs();
        }

        try {
            FileOutputStream fos = new FileOutputStream(DATA_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this.taskList);
            oos.close();
            fos.close();
            return true;
        } catch (IOException e) {
            this.consoleWriter.print("Помилка збереження завдань " + e.getMessage());
        }
        return false;
    }

    @Override
    public void load() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("Файл завдань не знайдено. Створено новий список.");
            return;
        }

        try  {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.taskList = (List<Task>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Завдання успішно завантажено");
        } catch (IOException | ClassNotFoundException e) {
            this.consoleWriter.print("Помилка завантаження завдань " + e);
            this.taskList = new ArrayList<>();
        }
    }
}
