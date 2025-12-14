package edu.chdtu.web2411.poliakov.lab3.facade;

import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.exception.ServiceException;
import edu.chdtu.web2411.poliakov.lab3.exception.TaskNotFoundException;
import edu.chdtu.web2411.poliakov.lab3.exception.UserNotFoundException;
import edu.chdtu.web2411.poliakov.lab3.model.Task;
import edu.chdtu.web2411.poliakov.lab3.service.TaskService;
import edu.chdtu.web2411.poliakov.lab3.service.UserService;

import java.util.Optional;

public class TaskServiceFacade extends TaskService {
    private TaskService taskService;
    private UserService userService;
    private ConsoleWriter consoleWriter;

    public TaskServiceFacade() {
        this.userService = UserService.getInstance();
        this.taskService = TaskService.getInstance();
        this.consoleWriter = new ConsoleWriter();
    }

    public boolean createAndAssignTask(Task task, int userId) throws ServiceException {
        boolean createdTask = this.taskService.add(task);

        if (!userService.isValidUser(userId)) {
            throw new UserNotFoundException("Пользователь не найден: " + userId);
        }

        userService.assignTask(userId, task);

        return createdTask;
    }

    public void deleteTask(int taskId, int userId) {
        if (!userService.isValidUser(userId)) {
            throw new UserNotFoundException("Пользователь не найден: " + userId);
        }

        Optional<Task> task = this.taskService.getById(taskId);

        if (task.isEmpty()) {
            throw new TaskNotFoundException("Задача не найдена");
        }

        this.taskService.remove(taskId);
        this.consoleWriter.print("Задача была удалена " + task + " пользователем " + userId);
    }
}
