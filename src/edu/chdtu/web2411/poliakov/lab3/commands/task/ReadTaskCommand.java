package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.model.Pagination;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.service.TaskService;


public class ReadTaskCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;
    private Pagination pagination;
    private TaskService taskService;

    public ReadTaskCommand(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
        this.pagination = new Pagination(5, 1, this.consoleWriter);
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
