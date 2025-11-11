package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.Task;
import edu.chdtu.web2411.poliakov.lab3.enums.TaskType;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.services.TaskService;

public class ChangeTaskStatusCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;

    public ChangeTaskStatusCommand() {
        this.consoleWriter = new ConsoleWriter();
    }

    @Override
    public void execute() {
        int id = consoleWriter.readInt("Введіть ID завдання: ");

        TaskService taskService = TaskService.getInstance();

        Task task = taskService.getAll().stream().filter(t -> t.getId() == id).findFirst().orElse(null);

        if (task == null) {
            consoleWriter.print("Задача не знайдена");
            return;
        }

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
