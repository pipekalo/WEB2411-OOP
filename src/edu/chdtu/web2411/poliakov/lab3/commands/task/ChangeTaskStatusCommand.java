package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.services.ConsoleService;
import edu.chdtu.web2411.poliakov.lab3.Task;
import edu.chdtu.web2411.poliakov.lab3.enums.TaskType;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;

import java.util.List;

public class ChangeTaskStatusCommand implements MenuCommand {
    private List<Task> taskList;
    private ConsoleService consoleService;

    public ChangeTaskStatusCommand(List<Task> taskList) {
        this.taskList = taskList;
        this.consoleService = new ConsoleService();
    }

    @Override
    public void execute() {
        int id = consoleService.readInt("Введіть ID завдання: ");
        Task task = taskList.stream().filter(t -> t.getId() == id).findFirst().orElse(null);

        if (task == null) {
            consoleService.print("Задача не знайдена");
            return;
        }

        consoleService.print("\nПоточний статус: " + task.getTaskType());
        consoleService.print("1. TODO");
        consoleService.print("2. IN_PROGRESS");
        consoleService.print("3. DONE");

        String choice = consoleService.readLine("Виберіть новий статус: ");
        TaskType taskType;
        switch (choice) {
            case "1":
                taskType = TaskType.TODO;
                break;
            case "2":
                taskType = TaskType.IN_PROGRESS;
                break;
            case "3":
                taskType = TaskType.DONE;
                break;
            default:
                consoleService.print("Неправильний вибір");
                return;
        }
        task.setTaskType(taskType);
        consoleService.print("Статус оновлено!");
    }
}
