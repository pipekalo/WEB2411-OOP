package edu.chdtu.web2411.poliakov.lab3;

import edu.chdtu.web2411.poliakov.lab3.commands.task.*;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.services.TaskService;

import java.io.IOException;
import java.util.*;

public class TaskManager {
    private Map<String, MenuCommand> operations;
    private ConsoleWriter consoleWriter;
    private TaskService taskService;

    public TaskManager() {
        this.consoleWriter = new ConsoleWriter();
        this.operations = new HashMap<>();

        initializeOperations();
    }

    private void initializeOperations() {
        operations.put("1", new CreateTaskCommand());
        operations.put("2", new ReadTaskCommand());
        operations.put("3", new UpdateTaskCommand());
        operations.put("4", new RemoveTaskCommand());
        operations.put("5", new ChangeTaskStatusCommand());
    }


    private void showMenu() {
        consoleWriter.print("\n╔════════════════════════════════════╗");
        consoleWriter.print("║      TASK MANAGER - ГОЛОВНЕ МЕНЮ   ║");
        consoleWriter.print("╠════════════════════════════════════╣");
        consoleWriter.print("║ 1. Додати завдання                 ║");
        consoleWriter.print("║ 2. Переглянути всі завдання        ║");
        consoleWriter.print("║ 3. Редагувати завдання             ║");
        consoleWriter.print("║ 4. Видалити завдання               ║");
        consoleWriter.print("║ 5. Змінити статус завдання         ║");
        consoleWriter.print("║ 0. Вихід                           ║");
        consoleWriter.print("╚════════════════════════════════════╝");
    }

    public void run() {
        while (true) {
            try {
                showMenu();
                String choice = consoleWriter.readLine("Виберіть дію: ");

                MenuCommand operation = operations.get(choice);
                if(operation != null && !choice.equals("0")) {
                    operation.execute();
                } else if(choice.equals("0")) {
                    break;
                }else {
                    consoleWriter.print("Невірний вибір. Спробуйте знову.");
                }
            } catch (IOException e) {
                this.consoleWriter.print("Помилка читання введення: " + e);
            }
        }
    }

}
