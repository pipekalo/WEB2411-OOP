package edu.chdtu.web2411.poliakov.lab3.service;

import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.model.Project;
import edu.chdtu.web2411.poliakov.lab3.exception.FileWriterException;
import edu.chdtu.web2411.poliakov.lab3.exception.SerializerException;
import edu.chdtu.web2411.poliakov.lab3.exception.ServiceException;
import edu.chdtu.web2411.poliakov.lab3.impls.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectService implements Service<Project> {
    private List<Project> projectList;
    private ConsoleWriter consoleWriter;

    private static String DATA_FILE = "data/projects.ser";

    protected ProjectService() {
        this.projectList = new ArrayList<>();
        this.consoleWriter = new ConsoleWriter();
        this.load();
    }

    private static class ProjectServiceHolder {
        private static final ProjectService INSTANCE = new ProjectService();
    }

    public static ProjectService getInstance() {
        return ProjectServiceHolder.INSTANCE;
    }

    @Override
    public boolean add(Project item) throws ServiceException {
        try {
            projectList.add(item);
            this.save();
            return true;
        } catch (Exception e) {
            throw new ServiceException("Ошибка при добавлении проекта", e);
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
            throw new FileWriterException(DATA_FILE, e);
        }
    }

    @Override
    public void load() {
        File file = new File(DATA_FILE);

        if (!file.exists()) {
            this.consoleWriter.print("Файл проектів не знайдено. Створено новий список.");
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
            System.err.println("Попередження: Не вдалося завантажити проекти: " + e);
            System.err.println("Створено новий список проектів.");
            this.projectList = new ArrayList<>();
        }
    }
}
