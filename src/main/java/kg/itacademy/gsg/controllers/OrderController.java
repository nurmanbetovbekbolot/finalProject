package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Order;
import kg.itacademy.gsg.entities.User;
import kg.itacademy.gsg.enums.Status;
import kg.itacademy.gsg.models.*;
import kg.itacademy.gsg.services.ClientTasksService;
import kg.itacademy.gsg.services.OrderService;
import kg.itacademy.gsg.services.PackageService;
import kg.itacademy.gsg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private PackageService packageService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientTasksService clientTasksService;

    User user;

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @GetMapping(value = "/list")
    public String getOrderList(@RequestParam(value = "search", required = false) String search, @Param("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @Param("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo, @PageableDefault(3) Pageable pageable, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Page<OrderModel> orderList;
        if (search != null) {
            orderList = orderService.findAllOrdersByName(search.toLowerCase(), pageable);
        } else if (dateFrom != null && dateTo != null) {
            orderList = orderService.findAllOrdersByDate(dateFrom, dateTo, pageable);
        } else {
            orderList = orderService.findAll(pageable);
        }
        model.addAttribute("userName", user.getEmail());
        model.addAttribute("orderList", orderList);
        return "admin/list_of_orders";
    }

    @GetMapping(value = "/{id}")
    public String orderInfo(@PathVariable("id") Long id, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Order o = orderService.getOrderById(id);
        model.addAttribute("userName", user.getEmail());
        model.addAttribute("add", true);
        model.addAttribute("order", o);
        return "admin/order_form";
    }

    @GetMapping(value = "/{id}/clientTasks")
    public String getClientTasks(@PathVariable("id") Long id, @PageableDefault(5) Pageable pageable, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Order o = orderService.getOrderById(id);
        Page<ClientTasksModel> clientTasksModels = clientTasksService.findAllClientTasksByOrderId(id, pageable);
        model.addAttribute("userName", user.getEmail());
        model.addAttribute("orderId", id);
        model.addAttribute("add", true);
        model.addAttribute("status", Status.values());
        model.addAttribute("order", o);
        model.addAttribute("taskModels", clientTasksModels);
        return "admin/list_of_clientTask";
    }

    @GetMapping(value = "/form")
    public String getCreateOrderForm(Model model, Authentication authentication) {
        getUserInfo(authentication);
        List<UserModel> userList = userService.getByRole("ROLE_USER");
        List<PackageModel> packageList = packageService.getAll();
        model.addAttribute("userName", user.getEmail());
        model.addAttribute("userList", userList);
        model.addAttribute("packageList", packageList);
        model.addAttribute("add", true);
        model.addAttribute("userName", user.getEmail());
        return "admin/order_form";
    }

    @PostMapping(value = "/create")
    public String addOrder(@Valid @ModelAttribute("order") OrderCreationModel orderCreationModel) {
        orderCreationModel.setManagerId(user.getId());
        orderService.saveOrder(orderCreationModel);
        return "redirect:/order/list";
    }

    @PostMapping(value = "/update/{id}")
    public String updateOrder(@Valid @ModelAttribute("order") OrderModel orderModel, @PathVariable("id") Long id) {
        orderModel.setId(id);
        orderService.updateOrder(orderModel);
        return "redirect:/order/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
        return "redirect:/order/list";
    }

    private void getUserInfo(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        user = userService.findByEmail(userPrincipal.getUsername());
    }
}
