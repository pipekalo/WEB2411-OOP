package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.Pagination;
import edu.chdtu.web2411.poliakov.lab3.Task;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.services.TaskService;


public class ReadTaskCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;
    private Pagination pagination;
    private TaskService taskService;

    public ReadTaskCommand() {
        this.consoleWriter = new ConsoleWriter();
        this.pagination = new Pagination(5, 1);
        this.taskService = TaskService.getInstance();
    }

    @Override
    public void execute() {
        if (this.taskService.getAll().isEmpty()) {
            consoleWriter.print("\nСписок порожній");
            return;
        }

        pagination.run();
    }
}
