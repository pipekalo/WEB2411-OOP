package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.Task;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.services.TaskService;


public class RemoveTaskCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;

    public RemoveTaskCommand() {
        this.consoleWriter = new ConsoleWriter();
    }

    @Override
    public void execute() {
        int id = consoleWriter.readInt("Введіть ID завдання: ");

        TaskService taskService = TaskService.getInstance();

        Task task = taskService.getAll().stream().filter(t -> t.getId() == id).findFirst().orElse(null);

        if(task == null) {
            consoleWriter.print("Задача не знайдена");
            return;
        }

        String answer = consoleWriter.readLine("Ви впевнені, що хочете видалити завдання? (y/n): ");
        if((answer.equals("y") || answer.equals("yes")) && taskService.remove(id)) {
                consoleWriter.print("Завдання видалено!");
        }
    }
}
