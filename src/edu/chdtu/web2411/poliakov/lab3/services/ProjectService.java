package edu.chdtu.web2411.poliakov.lab3.services;

import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.Project;
import edu.chdtu.web2411.poliakov.lab3.impls.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectService implements Service<Project> {
    private static ProjectService instance;
    private List<Project> projectList;
    private ConsoleWriter consoleWriter;

    private static String DATA_FILE = "data/projects.ser";

    private ProjectService() {
        this.projectList = new ArrayList<>();
        this.consoleWriter = new ConsoleWriter();
        this.load();
    }

    public static ProjectService getInstance() {
        if (instance == null) {
            instance = new ProjectService();
        }
        return instance;
    }

    @Override
    public boolean add(Project item) {
        try {
            projectList.add(item);
            this.save();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Project> getAll() {
        return new ArrayList<>(projectList);
    }

    @Override
    public Optional<Project> getById(int id) {
        return projectList.stream().filter(project -> project.getId() == id).findFirst();
    }

    @Override
    public boolean remove(int id) {
        boolean removedProject = projectList.removeIf(project -> project.getId() == id);
        if (removedProject) {
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

            oos.writeObject(this.projectList);
            oos.close();
            fos.close();
            return true;
        } catch (IOException e) {
            this.consoleWriter.print("Помилка збереження проектів " + e);
        }
        return false;
    }

    @Override
    public void load() {
        File file = new File(DATA_FILE);

        if (!file.exists()) {
            this.consoleWriter.print("Файл проектів не знайдено. Створено новий проект.");
            return;
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.projectList = (List<Project>) ois.readObject();
            ois.close();
            fis.close();
            this.consoleWriter.print("Проекти успішно завантажено");
        } catch (IOException | ClassNotFoundException e) {
            this.consoleWriter.print("Помилка завантаження проектів " + e);
            this.projectList = new ArrayList<>();
        }
    }
}
