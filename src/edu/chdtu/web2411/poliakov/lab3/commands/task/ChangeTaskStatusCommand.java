package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.model.Task;
import edu.chdtu.web2411.poliakov.lab3.enums.TaskType;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.service.TaskService;

import java.util.Optional;

public class ChangeTaskStatusCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;
    private TaskService taskService;

    public ChangeTaskStatusCommand(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
        this.taskService = TaskService.getInstance();
    }

    @Override
    public void execute() {
        int id = consoleWriter.readInt("Введіть ID завдання: ");

        Optional<Task> taskOptional = this.taskService.getById(id);

        if (taskOptional.isEmpty()) {
            consoleWriter.print("Задача не знайдена");
            return;
        }

        Task task = taskOptional.get();

        consoleWriter.print("\nПоточний статус: " + task.getTaskType());
        consoleWriter.print("1. TODO");
        consoleWriter.print("2. IN_PROGRESS");
        consoleWriter.print("3. DONE");

        String choice = consoleWriter.readLine("Виберіть новий статус: ");
        TaskType taskType = switch (choice) {
            case "1" -> TaskType.TODO;
            case "2" -> TaskType.IN_PROGRESS;
            case "3" -> TaskType.DONE;
            default -> {
                consoleWriter.print("Неправильний вибір");
                yield null;
            }
        };

        if(taskType == null) {
            return;
        }

        boolean success = taskService.changeStatus(id, taskType);

        if(success) {
            consoleWriter.print("Статус оновлено!");
        } else {
            consoleWriter.print("ПРомилка оновлення статусу");
        }
    }
}
