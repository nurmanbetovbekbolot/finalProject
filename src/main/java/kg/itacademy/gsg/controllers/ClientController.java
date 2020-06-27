package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.User;
import kg.itacademy.gsg.enums.Status;
import kg.itacademy.gsg.models.ClientTasksModel;
import kg.itacademy.gsg.models.OrderModel;
import kg.itacademy.gsg.services.ClientTasksService;
import kg.itacademy.gsg.services.OrderService;
import kg.itacademy.gsg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClientTasksService clientTasksService;

    User user;

    @GetMapping(value = "/order/list")
    public String getClientsOrderList(@PageableDefault(5) Pageable pageable, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Page<OrderModel> clientOrderList = orderService.findAllOrdersByClientId(user.getId(), pageable);
        model.addAttribute("orderList", clientOrderList);
        model.addAttribute("userName", user.getEmail());
        model.addAttribute("bool", true);
        return "admin/list_of_orders";
    }

//    @GetMapping(value = "/task/list")
//    public String getAllClientTasksByStatus(@Param("status") Status status, @PageableDefault(6) Pageable pageable, Model model, Authentication authentication) {
//        getUserInfo(authentication);
//        Page<ClientTasksModel> clientTaskList =clientTasksService.findAllClientTasksByStatus(status,pageable);
//        model.addAttribute("taskList", clientTaskList);
//        model.addAttribute("bool", true);
//        model.addAttribute("userName", user.getEmail());
//        return "admin/list_of_clientTask";
//    }

    private void getUserInfo(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        user = userService.findByEmail(userPrincipal.getUsername());
    }
}