package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.model.Task;
import edu.chdtu.web2411.poliakov.lab3.exception.ServiceException;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.service.TaskService;

import java.io.IOException;


public class CreateTaskCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;
    private TaskService taskService;

    public CreateTaskCommand(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
        this.taskService = TaskService.getInstance();
    }

    @Override
    public void execute() throws IOException, ServiceException {
        String title = consoleWriter.readLine("Введіть заголовок завдання: ");
        String description = consoleWriter.readLine("Введіть опис завдання: ");
        int daysToDeadline = consoleWriter.readInt("Введіть термін завдання (в днях): ");
        int priority = consoleWriter.readInt("Введіть пріоритетність завдання від 1 до 5: ");

        if (priority < 1 || priority > 5) {
            consoleWriter.print("Пріоритет повинен бути від 1 до 5");
            return;
        }

        Task task = new Task(this.taskService.getAll().size() + 1, title, description, daysToDeadline, priority);

        if (taskService.add(task)) {
            consoleWriter.print("Завдання створено. ID: " + task.getId());
        }
    }
}
