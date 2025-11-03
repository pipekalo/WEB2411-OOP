package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.services.ConsoleService;
import edu.chdtu.web2411.poliakov.lab3.Task;
import edu.chdtu.web2411.poliakov.lab3.enums.TaskType;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;

import java.util.List;

public class UpdateTaskCommand implements MenuCommand {
    private List<Task> taskList;
    private ConsoleService consoleService;

    public UpdateTaskCommand(List<Task> taskList) {
        this.taskList = taskList;
        this.consoleService = new ConsoleService();
    }

    @Override
    public void execute() {
        int id = consoleService.readInt("Введіть ID для редагування: ");
        Task task = taskList.stream().filter(t -> t.getId() == id).findFirst().orElse(null);

        if (task == null) {
            consoleService.print("Завдання не знайдено");
            return;
        }

        String title = consoleService.readLine("Новий заголовок для завдання: ");
        if (!title.isEmpty()) {
            task.setTitle(title);
        }

        String description = consoleService.readLine("Новий опис: ");
        if (!description.isEmpty()) {
            task.setDescription(description);
        }

        int daysToDeadline = consoleService.readInt("Новий дедлайн завдання: ");
        task.setDeadline(daysToDeadline);

        String taskType = consoleService.readLine("Новий статус завдання (TODO/IN_PROGRESS/DONE): ");
        if (!taskType.isEmpty()) {
            try {
                TaskType type = TaskType.valueOf(taskType);
                task.setTaskType(type);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Неправильний статус завдання! " + e);
            }
        }

        String priorityStr = consoleService.readLine("Новий пріоритет завдання від 1 до 5: ");
        if (!priorityStr.isEmpty()) {
            try {
                int priority = Integer.parseInt(priorityStr);
                if (priority >= 1 && priority <= 5) {
                    task.setPriority(priority);
                }
            } catch (NumberFormatException e) {
                consoleService.print("Неправильний формат пріоритету. " + e);
            }
        }

        consoleService.print("Завдання оновлено!");
    }
}
