package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.service.TaskService;


public class RemoveTaskCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;
    private TaskService taskService;

    public RemoveTaskCommand(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
        this.taskService = TaskService.getInstance();
    }

    @Override
    public void execute() {
        int id = consoleWriter.readInt("Введіть ID завдання: ");

        String answer = consoleWriter.readLine("Ви впевнені, що хочете видалити завдання? (y/n): ");
        if((answer.equals("y") || answer.equals("yes")) && taskService.remove(id)) {
                consoleWriter.print("Завдання видалено!");
        }
    }
}
