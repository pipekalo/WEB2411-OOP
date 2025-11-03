package edu.chdtu.web2411.poliakov.lab3.commands.task;

import edu.chdtu.web2411.poliakov.lab3.services.ConsoleService;
import edu.chdtu.web2411.poliakov.lab3.Pagination;
import edu.chdtu.web2411.poliakov.lab3.Task;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;

import java.util.List;

public class ReadTaskCommand implements MenuCommand {
    private List<Task> taskList;
    private ConsoleService consoleService;
    private Pagination pagination;

    public ReadTaskCommand(List<Task> taskList) {
        this.taskList = taskList;
        this.consoleService = new ConsoleService();
        this.pagination = new Pagination(5, 1, this.taskList);
    }

    @Override
    public void execute() {
        if (taskList.isEmpty()) {
            consoleService.print("\nСписок порожній");
            return;
        }

        pagination.run();
    }
}
