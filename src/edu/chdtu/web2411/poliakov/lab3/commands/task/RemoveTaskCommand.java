package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.facade.TaskServiceFacade;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.service.TaskService;


public class RemoveTaskCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;
    private TaskServiceFacade taskServiceFacade;

    public RemoveTaskCommand(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
        this.taskServiceFacade = (TaskServiceFacade) TaskServiceFacade.getInstance();
    }

    @Override
    public void execute() {
        int taskId = consoleWriter.readInt("Введіть ID завдання: ");
        int userId = consoleWriter.readInt("Введіть свое ID: ");
        String answer = consoleWriter.readLine("Ви впевнені, що хочете видалити завдання? (y/n): ");
        if((answer.equals("y") || answer.equals("yes"))) {
            this.taskServiceFacade.deleteTask(taskId, userId);
        }
    }
}
