package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Notification;
import kg.itacademy.gsg.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = "/list")
    public String getNotificationList(Model model) {
        List<Notification> notificationList = notificationService.getAllNotifications();
        model.addAttribute("notificationList", notificationList);
        model.addAttribute("bool", true);
        return "notificationList";
    }

    @GetMapping(value = "/{id}")
    public String notificationInfo(@PathVariable("id") Long id, Model model) {
        Notification notification = notificationService.getNotificationById(id);
        model.addAttribute("notification", notification);
        return "notificationDetail";
    }

    @PostMapping(value = "/create")
    public String addNotification(@Valid @ModelAttribute("notification") Notification notification) {
        notificationService.saveNotification(notification);
        return "redirect:/notification/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        notificationService.deleteNotificationById(id);
        return "redirect:/notification/list";
    }
}
