package edu.chdtu.web2411.poliakov.lab3.model;

import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.enums.TaskType;
import edu.chdtu.web2411.poliakov.lab3.service.TaskService;

import java.util.List;

public class Pagination {
    private int currentPage;
    private int limitPerPage;
    private TaskService taskService;
    private List<Task> displayTasks;
    private boolean isFiltered = false;

    private ConsoleWriter consoleWriter;

    public Pagination(int limitPerPage, int currentPage, ConsoleWriter consoleWriter) {
        this.limitPerPage = limitPerPage;
        this.currentPage = currentPage;
        this.consoleWriter = consoleWriter;
        this.taskService = TaskService.getInstance();
        this.displayTasks = null;
        this.isFiltered = false;
    }

    private List<Task> getDisplayTasks() {
        return isFiltered ? this.displayTasks : this.taskService.getAll();
    }

    private int getCurrentPage() {
        return this.currentPage;
    }

    private int getTotalPages() {
        return (int) Math.ceil((double) this.getDisplayTasks().size() / this.limitPerPage);
    }

    private void nextPage() {
        if (this.currentPage < this.getTotalPages()) {
            this.currentPage++;
        } else {
            consoleWriter.print("Ви на останній сторінці!");
        }
    }

    private void prevPage() {
        if (this.currentPage > 1) {
            this.currentPage--;
        } else {
            consoleWriter.print("Ви на першій сторінці!");
        }
    }

    private void goToPage(int page) {
        if (page >= 1 && page <= this.getTotalPages()) {
            this.currentPage = page;
        } else {
            consoleWriter.print("Неправильний номер сторінки!");
        }
    }

    private boolean hasNextPage() {
        return this.currentPage < this.getTotalPages();
    }

    private boolean hasPrevPage() {
        return this.currentPage > 1;
    }

    private void filterByPriority() {
        int priority = consoleWriter.readInt("Введите число от 1 до 5 чтобы отфильтровать список: ");
        this.displayTasks = this.taskService.getAll().stream().filter(task -> task.getPriority() == priority).toList();
        this.currentPage = 1;

        this.isFiltered = true;

        if (this.displayTasks.isEmpty()) {
            consoleWriter.print("Немає завдань з таким пріоритетом!");
        }
    }

    private void filterByStatus() {
        consoleWriter.print("\nВсі статуси:");
        consoleWriter.print("1. TODO");
        consoleWriter.print("2. IN_PROGRESS");
        consoleWriter.print("3. DONE");

        String choice = consoleWriter.readLine("Виберіть статус для фільтрації за ним: ");

        TaskType taskType = switch (choice) {
            case "1" -> TaskType.TODO;
            case "2" -> TaskType.IN_PROGRESS;
            case "3" -> TaskType.DONE;
            default -> {
                consoleWriter.print("Неправильний вибір");
                yield null;
            }
        };
        this.isFiltered = true;
        this.displayTasks = this.taskService.getAll().stream().filter(task -> task.getTaskType().equals(taskType)).toList();
    }

    private void clearFilter() {
        this.displayTasks = List.copyOf(this.taskService.getAll());
        this.currentPage = 1;
        this.isFiltered = false;
        consoleWriter.print("Фільтр скасовано!");
    }

    public void run() {
        label:
        while (true) {
            this.displayPage(this.getCurrentPage(), this.getTotalPages());

            consoleWriter.print("\n[N] - Наступна сторінка | [P] - Попередня сторінка | [F+P] - Фільтрація за пріоритетністю | [F+S] - Фільтрація за статусом | [C] - Скасувати фільтрацію | [Q] - Вихід");
            String input = consoleWriter.readLine("Введіть номер сторінки (1-" + this.getTotalPages() + ") або команду:");

            switch (input) {
                case "Q" -> {
                    break label;
                }
                case "N" -> {
                    if (this.hasNextPage()) {
                        this.nextPage();
                    }
                }
                case "P" -> {
                    if (this.hasPrevPage()) {
                        this.prevPage();
                    }
                }
                case "F+P", "FP" -> this.filterByPriority();
                case "F+S", "FS" -> this.filterByStatus();
                case "C" -> this.clearFilter();
                default -> {
                    try {
                        int page = Integer.parseInt(input);
                        this.goToPage(page);
                    } catch (NumberFormatException e) {
                        consoleWriter.print("Неправильне введення!");
                    }
                }
            }
        }
    }

    private void displayPage(int currentPage, int totalPages) {
        consoleWriter.print("\n╔════════════════════════════════════════════════════════╗");
        consoleWriter.print("║                    ВСІ ЗАВДАННЯ                        ║");
        consoleWriter.print("║               Сторінка " + currentPage + " з " + totalPages + "                           ║");
        consoleWriter.print("╚════════════════════════════════════════════════════════╝\n");

        List<Task> taskList = this.getDisplayTasks();

        int startIndex = (currentPage - 1) * limitPerPage;
        int endIndex = Math.min(startIndex + limitPerPage, taskList.size());

        for (int i = startIndex; i < endIndex; i++) {
            consoleWriter.print((i + 1) + ". " + taskList.get(i).getInfo());
        }

        consoleWriter.print("\nВсього завдань: " + taskList.size());
    }
}
