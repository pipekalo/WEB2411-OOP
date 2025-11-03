package edu.chdtu.web2411.poliakov.lab3;

import edu.chdtu.web2411.poliakov.lab3.enums.TaskType;
import edu.chdtu.web2411.poliakov.lab3.services.ConsoleService;

import java.util.List;

public class Pagination {
    private int currentPage;
    private int limitPerPage;
    private List<Task> taskList;
    private  List<Task> originaTasklList;

    private ConsoleService consoleService;

    public Pagination(int limitPerPage, int currentPage, List<Task> taskList) {
        this.limitPerPage = limitPerPage;
        this.currentPage = currentPage;
        this.taskList = taskList;
        this.originaTasklList = taskList;
        this.consoleService = new ConsoleService();
    }

    private int getCurrentPage() {
        return this.currentPage;
    }

    private int getTotalPages() {
        return (int) Math.ceil((double) this.taskList.size() / this.limitPerPage);
    }

    private void nextPage() {
        if (this.currentPage < this.getTotalPages()) {
            this.currentPage++;
        } else {
            consoleService.print("Ви на останній сторінці!");
        }
    }

    private void prevPage() {
        if (this.currentPage > 1) {
            this.currentPage--;
        } else {
            consoleService.print("Ви на першій сторінці!");
        }
    }

    private void goToPage(int page) {
        if (page >= 1 && page <= this.getTotalPages()) {
            this.currentPage = page;
        } else {
            consoleService.print("Неправильний номер сторінки!");
        }
    }

    private boolean hasNextPage() {
        return this.currentPage < this.getTotalPages();
    }

    private boolean hasPrevPage() {
        return this.currentPage > 1;
    }

    private void filterByPriority() {
        int priority = consoleService.readInt("Введите число от 1 до 5 чтобы отфильтровать список: ");
        this.taskList = taskList.stream().filter(task -> task.getPriority() == priority).toList();
    }

    private void filterByStatus() {
        consoleService.print("\nВсі статуси:");
        consoleService.print("1. TODO");
        consoleService.print("2. IN_PROGRESS");
        consoleService.print("3. DONE");

        String choice = consoleService.readLine("Виберіть статус для фільтрації за ним: ");

        TaskType taskType;
        switch (choice) {
            case "1":
                taskType = TaskType.TODO;
                break;
            case "2":
                taskType = TaskType.IN_PROGRESS;
                break;
            case "3":
                taskType = TaskType.DONE;
                break;
            default:
                consoleService.print("Неправильний вибір");
                return;
        }

        this.taskList = taskList.stream().filter(task -> task.getTaskType().equals(taskType)).toList();
    }

    public void run() {
        label:
        while (true) {
            displayPage(this.getCurrentPage(), this.getTotalPages());

            consoleService.print("\n[N] - Наступна сторінка | [P] - Попередня сторінка | [F+P] - Фільтрація за пріоритетністю | [F+S] - Фільтрація за статусом | [C] - Скасувати фільтрацію | [Q] - Вихід");
            String input = consoleService.readLine("Введіть номер сторінки (1-" + this.getTotalPages() + ") або команду:");

            switch (input) {
                case "Q":
                    break label;
                case "N":
                    if (this.hasNextPage()) {
                        this.nextPage();
                    }
                    break;
                case "P":
                    if (this.hasPrevPage()) {
                        this.prevPage();
                    }
                    break;
                case "F+P":
                case "FP":
                    this.filterByPriority();
                    break;
                case "F+S":
                case "FS":
                    this.filterByStatus();
                    break;
                case "C":
                    taskList = originaTasklList;
                    break;
                default:
                    try {
                        int page = Integer.parseInt(input);
                        this.goToPage(page);
                    } catch (NumberFormatException e) {
                        consoleService.print("Неправильне введення!");
                    }
                    break;
            }
        }
    }

    private void displayPage(int currentPage, int totalPages) {
        consoleService.print("\n╔════════════════════════════════════════════════════════╗");
        consoleService.print("║                    ВСІ ЗАВДАННЯ                          ║");
        consoleService.print("║               Сторінка " + currentPage + " з " + totalPages + "                           ║");
        consoleService.print("╚════════════════════════════════════════════════════════╝\n");

        int startIndex = (currentPage - 1) * limitPerPage;
        int endIndex = Math.min(startIndex + limitPerPage, taskList.size());

        for (int i = startIndex; i < endIndex; i++) {
            consoleService.print((i + 1) + ". " + taskList.get(i).getInfo());
        }

        consoleService.print("\nВсього завдань: " + taskList.size());
    }
}
