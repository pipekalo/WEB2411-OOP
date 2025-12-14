package edu.chdtu.web2411.poliakov.lab3.service;

import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.exception.FileWriterException;
import edu.chdtu.web2411.poliakov.lab3.exception.ServiceException;
import edu.chdtu.web2411.poliakov.lab3.impls.Service;
import edu.chdtu.web2411.poliakov.lab3.model.Project;
import edu.chdtu.web2411.poliakov.lab3.model.Task;
import edu.chdtu.web2411.poliakov.lab3.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService implements Service<User> {
    private List<User> userList;
    private ConsoleWriter consoleWriter;

    private static String DATA_FILE = "data/users.ser";

    public UserService() {
        this.userList.add(new User(1, "Alex", "alex123@eu.com"));
        this.userList.add(new User(2, "Diana", "diana.i@eu.com"));
        this.userList.add(new User(3, "John", "asdkhasd@eu.com"));
    }

    @Override
    public boolean add(User item) throws ServiceException {
        try {
            userList.add(item);
            this.save();
            return true;
        } catch (Exception e) {
            throw new ServiceException("Ошибка при добавлении проекта", e);
        }
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(this.userList);
    }

    @Override
    public Optional<User> getById(int id) {
        return this.userList.stream().filter(user -> user.getId() == id).findFirst();
    }

    @Override
    public boolean remove(int id) {
        boolean removedUser = this.userList.removeIf(user -> user.getId() == id);
        if (removedUser) {
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

            oos.writeObject(this.userList);
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

            this.userList = (List<User>) ois.readObject();
            ois.close();
            fis.close();
            this.consoleWriter.print("Проекти успішно завантажено");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Попередження: Не вдалося завантажити проекти: " + e);
            System.err.println("Створено новий список проектів.");
            this.userList = new ArrayList<>();
        }
    }

    public boolean isValidUser(int userId) {
        boolean valid = userList.contains(userId);
        this.consoleWriter.print("Валидация пользователя " + userId + ": " + valid);
        return valid;
    }

    public void assignTask(int userId, Task task) {
        User user = userList.get(userId);
        if(user != null) {
            user.addAssignedTask(task);
            this.consoleWriter.print("Задача " + task.getId() + " назначена пользователю " + user.getName());
        }
    }

    private static class UserServiceHolder {
        private static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return UserServiceHolder.INSTANCE;
    }


}
