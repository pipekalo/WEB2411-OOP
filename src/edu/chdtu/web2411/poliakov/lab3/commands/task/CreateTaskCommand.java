package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.services.ConsoleService;
import edu.chdtu.web2411.poliakov.lab3.Task;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;

import java.util.List;

public class CreateTaskCommand implements MenuCommand {
    private List<Task> taskList;
    private ConsoleService consoleService;

    public CreateTaskCommand(List<Task> taskList) {
        this.taskList = taskList;
        this.consoleService = new ConsoleService();
    }

    @Override
    public void execute() {
        String title = consoleService.readLine("Введіть заголовок завдання: ");
        String description = consoleService.readLine("Введіть опис завдання: ");
        int daysToDeadline = consoleService.readInt("Введіть термін завдання (в днях): ");
        int priority = consoleService.readInt("Введіть пріоритетність завдання від 1 до 5: ");

        if (priority < 1 || priority > 5) {
            consoleService.print("Пріоритет повинен бути від 1 до 5");
            return;
        }

        Task task = new Task(taskList.size() + 1, title, description, daysToDeadline, priority);
        taskList.add(task);
        consoleService.print("Завдання створено. ID: " + task.getId());
    }
}
