package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.Task;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.services.TaskService;

import java.io.IOException;


public class CreateTaskCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;

    public CreateTaskCommand() {
        this.consoleWriter = new ConsoleWriter();
    }

    @Override
    public void execute() throws IOException {
        String title = consoleWriter.readLine("Введіть заголовок завдання: ");
        String description = consoleWriter.readLine("Введіть опис завдання: ");
        int daysToDeadline = consoleWriter.readInt("Введіть термін завдання (в днях): ");
        int priority = consoleWriter.readInt("Введіть пріоритетність завдання від 1 до 5: ");

        if (priority < 1 || priority > 5) {
            consoleWriter.print("Пріоритет повинен бути від 1 до 5");
            return;
        }

        TaskService taskService = TaskService.getInstance();

        Task task = new Task(taskService.getAll().size() + 1, title, description, daysToDeadline, priority);

        if (taskService.add(task)) {
            consoleWriter.print("Завдання створено. ID: " + task.getId());
        }
    }
}
