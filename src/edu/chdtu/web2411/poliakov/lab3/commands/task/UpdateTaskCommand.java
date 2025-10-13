package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.model.Task;
import edu.chdtu.web2411.poliakov.lab3.enums.TaskType;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.service.TaskService;

import java.util.Optional;

public class UpdateTaskCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;
    private TaskService taskService;

    public UpdateTaskCommand(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
        this.taskService = TaskService.getInstance();
    }

    @Override
    public void execute() {
        int id = consoleWriter.readInt("Введіть ID для редагування: ");

        Optional<Task> taskOptional = this.taskService.getById(id);

        if (taskOptional.isEmpty()) {
            consoleWriter.print("Завдання не знайдено");
            return;
        }

        Task task = taskOptional.get();

        String title = consoleWriter.readLine("Новий заголовок для завдання: ");
        if (!title.isEmpty()) {
            task.setTitle(title);
        }

        String description = consoleWriter.readLine("Новий опис: ");
        if (!description.isEmpty()) {
            task.setDescription(description);
        }

        int daysToDeadline = consoleWriter.readInt("Новий дедлайн завдання: ");
        task.setDeadline(daysToDeadline);

        String taskType = consoleWriter.readLine("Новий статус завдання (TODO/IN_PROGRESS/DONE): ");
        if (!taskType.isEmpty()) {
            try {
                TaskType type = TaskType.valueOf(taskType);
                task.setTaskType(type);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Неправильний статус завдання! " + e);
            }
        }

        String priorityStr = consoleWriter.readLine("Новий пріоритет завдання від 1 до 5: ");
        if (!priorityStr.isEmpty()) {
            try {
                int priority = Integer.parseInt(priorityStr);
                if (priority >= 1 && priority <= 5) {
                    task.setPriority(priority);
                }
            } catch (NumberFormatException e) {
                consoleWriter.print("Неправильний формат пріоритету. " + e);
            }
        }

        consoleWriter.print("Завдання оновлено!");
    }
}
