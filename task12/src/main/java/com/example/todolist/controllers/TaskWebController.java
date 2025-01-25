package com.example.todolist.controllers;

import com.example.todolist.gateway.FileGateway;
import com.example.todolist.model.Task;
import com.example.todolist.model.TaskStatus;
import com.example.todolist.service.TaskService;
import com.example.todolist.service.TaskSortingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс TaskWebController предоставляет контроллеры для взаимодействия с задачами в списке дел через веб-интерфейс.
 */

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskWebController {

    private final TaskService taskService;
    private final FileGateway fileGateway;
    private final TaskSortingService taskSortingService;

    /**
     * Метод для получения отсортированного списка задач и отображения их на веб-странице.
     * @param sortBy Ключ стратегии сортировки задач.
     * @param model Объект Model для передачи данных на веб-страницу.
     * @return Название представления (шаблона) для отображения списка задач.
     */
    @GetMapping("/sorted")
    public String getSortedTasks(@RequestParam(required = false) String sortBy,
                                 Model model) {
        List<Task> tasks = taskSortingService.getSortedTasks(sortBy);
        model.addAttribute("tasks", tasks);
        return "taskList";
    }

    /**
     * Метод для получения списка всех задач и отображения их на веб-странице.
     * @param model Объект Model для передачи данных на веб-страницу.
     * @return Название представления (шаблона) для отображения списка задач.
     */
    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "taskList";
    }

    /**
     * Метод для получения списка задач по указанному статусу и отображения их на веб-странице.
     * @param status Статус задач, по которому осуществляется фильтрация.
     * @param model Объект Model для передачи данных на веб-страницу.
     * @return Название представления (шаблона) для отображения списка задач.
     */
    @GetMapping("/status/{status}")
    public String getTasksByStatus(@PathVariable TaskStatus status,
                                   Model model) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        model.addAttribute("tasks", tasks);
        return "taskList";
    }

    /**
     * Метод для отображения формы добавления новой задачи.
     * @param model Объект Model для передачи данных на веб-страницу.
     * @return Название представления (шаблона) для отображения формы добавления задачи.
     */
    @GetMapping("/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "addTaskForm";
    }

    /**
     * Метод для обработки запроса на добавление новой задачи.
     * @param task Объект Task с данными новой задачи.
     * @return Редирект на страницу со списком задач после успешного добавления.
     */
    @PostMapping("/add")
    public String addTask(@ModelAttribute("task") Task task) {
        taskService.addTask(task);

        // Отправляем объект Task в канал для записи в файл
        fileGateway.writeToFile("tasks.txt", task);

        return "redirect:/tasks";
    }

    /**
     * Метод для отображения формы редактирования задачи.
     * @param id Идентификатор задачи для редактирования.
     * @param model Объект Model для передачи данных на веб-страницу.
     * @return Название представления (шаблона) для отображения формы редактирования задачи.
     */
    @GetMapping("/{id}/edit")
    public String showEditTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "editTaskForm";
    }

    /**
     * Метод для обработки запроса на обновление задачи.
     * @param id Идентификатор задачи для обновления.
     * @param task Объект Task с обновленными данными задачи.
     * @return Редирект на страницу со списком задач после успешного обновления.
     */
    @PostMapping("/{id}/edit")
    public String updateTask(@PathVariable Long id,
                             @ModelAttribute("task") Task task) {
        taskService.updateTask(id, task);
        return "redirect:/tasks";
    }

    /**
     * Метод для обработки запроса на обновление статуса задачи.
     * @param id Идентификатор задачи для обновления статуса.
     * @param status Новый статус задачи.
     * @return Редирект на страницу со списком задач после успешного обновления статуса.
     */
    @PostMapping("/{id}/status/{status}")
    public String updateTaskStatus(@PathVariable Long id,
                                   @PathVariable TaskStatus status) {
        taskService.updateTaskStatus(id, status);
        return "redirect:/tasks";
    }

    /**
     * Метод для обработки запроса на удаление задачи.
     * @param id Идентификатор задачи для удаления.
     * @return Редирект на страницу со списком задач после успешного удаления.
     */
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

}
