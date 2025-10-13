package edu.chdtu.web2411.poliakov.lab3;

import edu.chdtu.web2411.poliakov.lab3.commands.task.*;
import edu.chdtu.web2411.poliakov.lab3.exception.ServiceException;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;

import java.io.IOException;
import java.util.*;

public class TaskManager {
    private Map<String, MenuCommand> operations;
    private ConsoleWriter consoleWriter;

    public TaskManager(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
        this.operations = new HashMap<>();

        initializeOperations();
    }

    private void initializeOperations() {
        operations.put("1", new CreateTaskCommand(this.consoleWriter));
        operations.put("2", new ReadTaskCommand(this.consoleWriter));
        operations.put("3", new UpdateTaskCommand(this.consoleWriter));
        operations.put("4", new RemoveTaskCommand(this.consoleWriter));
        operations.put("5", new ChangeTaskStatusCommand(this.consoleWriter));
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
            } catch (IOException | ServiceException e) {
                this.consoleWriter.print("Помилка читання введення: " + e);
            }
        }
    }

}
