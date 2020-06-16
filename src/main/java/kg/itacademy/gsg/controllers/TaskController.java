package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Task;
import kg.itacademy.gsg.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping(value = "/list")
    public String getTaskList(Model model) {
        List<Task> taskList = taskService.getAllTasks();
        model.addAttribute("taskList", taskList);
        model.addAttribute("bool", true);
        return "taskList";
    }

    @GetMapping(value = "/{id}")
    public String taskInfo(@PathVariable("id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "taskDetail";
    }

    @PostMapping(value = "/create")
    public String addTask(@Valid @ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/task/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/task/list";
    }
}
