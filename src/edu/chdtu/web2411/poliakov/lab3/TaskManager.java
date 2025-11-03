package edu.chdtu.web2411.poliakov.lab3;

import edu.chdtu.web2411.poliakov.lab3.commands.task.*;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.services.ConsoleService;

import java.util.*;

public class TaskManager {
    protected List<Task> taskList;
    private Map<String, MenuCommand> operations;
    private ConsoleService consoleService;

    public TaskManager(List<Task> taskList) {
        this.taskList = taskList;
        this.consoleService = new ConsoleService();
        this.operations = new HashMap<>();

        initializeOperations();
    }

    private void initializeOperations() {
        operations.put("1", new CreateTaskCommand(taskList));
        operations.put("2", new ReadTaskCommand(taskList));
        operations.put("3", new UpdateTaskCommand(taskList));
        operations.put("4", new RemoveTaskCommand(taskList));
        operations.put("5", new ChangeTaskStatusCommand(taskList));
    }


    private void showMenu() {
        consoleService.print("\n╔════════════════════════════════════╗");
        consoleService.print("║      TASK MANAGER - ГОЛОВНЕ МЕНЮ   ║");
        consoleService.print("╠════════════════════════════════════╣");
        consoleService.print("║ 1. Додати завдання                 ║");
        consoleService.print("║ 2. Переглянути всі завдання        ║");
        consoleService.print("║ 3. Редагувати завдання             ║");
        consoleService.print("║ 4. Видалити завдання               ║");
        consoleService.print("║ 5. Змінити статус завдання         ║");
        consoleService.print("║ 0. Вихід                           ║");
        consoleService.print("╚════════════════════════════════════╝");
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void run() {
        while (true) {
            showMenu();
            String choice = consoleService.readLine("Виберіть дію: ");

            MenuCommand operation = operations.get(choice);
            if(operation != null && !choice.equals("0")) {
                operation.execute();
            } else if(choice.equals("0")) {
                break;
            }else {
                consoleService.print("Невірний вибір. Спробуйте знову.");
            }
        }
    }

}
