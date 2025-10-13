package edu.chdtu.web2411.poliakov.lab3.service;

import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.model.Task;
import edu.chdtu.web2411.poliakov.lab3.enums.TaskType;
import edu.chdtu.web2411.poliakov.lab3.exception.FileWriterException;
import edu.chdtu.web2411.poliakov.lab3.exception.ServiceException;
import edu.chdtu.web2411.poliakov.lab3.impls.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskService implements Service<Task> {
    private List<Task> taskList;
    private ConsoleWriter consoleWriter;

    private static String DATA_FILE = "data/tasks.ser";

    protected TaskService() {
        this.taskList = new ArrayList<>();
        this.consoleWriter = new ConsoleWriter();
        this.load();
    }

    private static class TaskServiceHolder {
        private static final TaskService INSTANCE = new TaskService();
    }

    public static TaskService getInstance() {
        return TaskServiceHolder.INSTANCE;
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
    public boolean add(Task item) throws ServiceException {
        try {
            taskList.add(item);
            this.save();
            return true;
        } catch (Exception e) {
            throw new ServiceException("Ошибка при добавлении задачи", e);
        }
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
        File dir = new File("data");
        if (!dir.exists()) {
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
            throw new FileWriterException(DATA_FILE, e);
        }
    }

    @Override
    public void load() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            this.consoleWriter.print("Файл завдань не знайдено. Створено новий список.");
            return;
        }

        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.taskList = (List<Task>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Завдання успішно завантажено");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Попередження: Не вдалося завантажити завдання: " + e.getMessage());
            System.err.println("Створено новий список завдань.");
            this.taskList = new ArrayList<>();
        }
    }
}
