package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Task;
import kg.itacademy.gsg.models.TaskModel;
import kg.itacademy.gsg.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    String username;

    @GetMapping(value = "/list")
    public String getTaskList(@PageableDefault(6) Pageable pageable, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Page<TaskModel> taskList = taskService.findAll(pageable);
        model.addAttribute("taskList", taskList);
        model.addAttribute("bool", true);
        model.addAttribute("userName", username);
        return "admin/list_of_tasks";
    }

    @GetMapping(value = "/{id}")
    public String taskInfo(@PathVariable("id") Long id, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("add", false);
        model.addAttribute("userName", username);
        return "admin/task_form";
    }

    @GetMapping(value = "/form")
    public String getCreateTaskForm(Model model, Authentication authentication) {
        getUserInfo(authentication);
        model.addAttribute("add", true);
        model.addAttribute("userName", username);
        return "admin/task_form";
    }

    @PostMapping(value = "/create")
    public String addTask(@Valid @ModelAttribute("task") TaskModel taskModel) {
        taskService.saveTask(taskModel);
        return "redirect:/task/list";
    }

    @PostMapping(value = "/update/{id}")
    public String updateTask(@Valid @ModelAttribute("task") TaskModel taskModel, @PathVariable("id") Long id) {
        taskModel.setId(id);
        taskService.updateTask(taskModel);
        return "redirect:/task/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/task/list";
    }

    private void getUserInfo(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        username = userPrincipal.getUsername();
    }
}
