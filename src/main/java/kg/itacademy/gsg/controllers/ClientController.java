package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Notification;
import kg.itacademy.gsg.entities.User;
import kg.itacademy.gsg.enums.Status;
import kg.itacademy.gsg.models.ClientTasksModel;
import kg.itacademy.gsg.models.CommentModel;
import kg.itacademy.gsg.models.NotificationModel;
import kg.itacademy.gsg.models.OrderModel;
import kg.itacademy.gsg.services.*;
import kg.itacademy.gsg.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClientTasksService clientTasksService;

    @Autowired
    MailSender mailSender;

    User user;

    @GetMapping(value = "/order/list")
    public String getClientsOrderList(@PageableDefault(5) Pageable pageable, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Page<OrderModel> clientOrderList = orderService.findAllOrdersByClientId(user.getId(), pageable);
        model.addAttribute("orderList", clientOrderList);
        model.addAttribute("userName", user.getEmail());
        return "admin/list_of_orders";
    }


    @PostMapping(value = "/order/{id}/clientTasks/{clientTaskId}/changeStatus")
    public String changeClientTasksStatus(@PathVariable("id") Long id, @PathVariable("clientTaskId") Long clientTaskId, @ModelAttribute("status") Status status, Authentication authentication) {
        getUserInfo(authentication);
//        mailSender.send(user.getEmail(), "Test", "Hello");
        clientTasksService.changeClientTasksStatus(clientTaskId, status, user.getRole().getRoleName());
        return "redirect:/order/" + id + "/clientTasks";
    }


    @GetMapping(value = "/task/list")
    public String getAllClientTasksByStatus(@Param("status") Status status, @PageableDefault(6) Pageable pageable, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Page<ClientTasksModel> clientTaskList = clientTasksService.findAllClientTasksByStatus(status, pageable);
        model.addAttribute("taskList", clientTaskList);
        model.addAttribute("bool", true);
        model.addAttribute("userName", user.getEmail());
        return "admin/list_of_clientTask";
    }

    @GetMapping(value = "/notification/list")
    public String getAllNotifications(@PageableDefault(6) Pageable pageable, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Page<NotificationModel> notificationModels = notificationService.getAllNotificationsByClientId(user.getId(), pageable);
        model.addAttribute("notificationList", notificationModels);
        model.addAttribute("userName", user.getEmail());
        return "user/list_of_notifications";
    }

    @GetMapping("/notification/{id}")
    public String detailNotificationPage(@PathVariable("id") Long id, Model model,Authentication authentication) {
        getUserInfo(authentication);
        notificationService.isOpened(id);
        Notification notification = notificationService.getNotificationById(id);
        model.addAttribute("userName", user.getEmail());
        model.addAttribute("notification", notification);
        return "user/notification_detail";
    }

    @GetMapping("/")
    public String clientPage(Model model,Authentication authentication) {
        getUserInfo(authentication);
        model.addAttribute("userName", user.getEmail());
        return "user/client";
    }

    private void getUserInfo(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        user = userService.findByEmail(userPrincipal.getUsername());
    }
}