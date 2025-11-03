package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.services.ConsoleService;
import edu.chdtu.web2411.poliakov.lab3.Task;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;

import java.util.List;

public class RemoveTaskCommand implements MenuCommand {
    private List<Task> taskList;
    private ConsoleService consoleService;

    public RemoveTaskCommand(List<Task> taskList) {
        this.taskList = taskList;
        this.consoleService = new ConsoleService();
    }

    @Override
    public void execute() {
        int id = consoleService.readInt("Введіть ID завдання: ");
        Task task = taskList.stream().filter(t -> t.getId() == id).findFirst().orElse(null);

        if(task == null) {
            consoleService.print("Задача не знайдена");
            return;
        }

        String answer = consoleService.readLine("Ви впевнені, що хочете видалити завдання? (y/n): ");
        if(answer.equals("y") || answer.equals("yes")) {
            taskList.removeIf(t -> t.getId() == task.getId());
            consoleService.print("Завдання видалено!");
        }
    }
}
