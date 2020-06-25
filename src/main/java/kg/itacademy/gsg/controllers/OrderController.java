package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Order;
import kg.itacademy.gsg.entities.User;
import kg.itacademy.gsg.models.*;
import kg.itacademy.gsg.services.OrderService;
import kg.itacademy.gsg.services.PackageService;
import kg.itacademy.gsg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
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

    User user;

    @GetMapping(value = "/list")
    public String getOrderList(@RequestParam(value = "search", required = false) String search, @Param("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @Param("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo, @PageableDefault(3) Pageable pageable, Model model) {
        Page<OrderModel> orderList;
        if (search != null) {
            orderList = orderService.findAllByName(search.toLowerCase(), pageable);
        } else if (dateFrom != null && dateTo != null) {
            orderList= orderService.findAllByDate(dateFrom,dateTo,pageable);
        } else {
            orderList = orderService.findAll(pageable);
        }

        model.addAttribute("orderList", orderList);
        model.addAttribute("bool", true);
        return "admin/list_of_orders";
    }

    @GetMapping(value = "/{id}")
    public String orderInfo(@PathVariable("id") Long id, Model model) {
        Order o = orderService.getOrderById(id);
        model.addAttribute("add",true);
        model.addAttribute("order", o);
        return "admin/order_form";
    }

    @GetMapping(value = "/{id}/clientTasks")
    public String getClientTasks(@PathVariable("id") Long id, Pageable pageable, Model model) {
        Order o = orderService.getOrderById(id);
        Page<ClientCategoryTasksModel> clientCategoryTasksModels = orderService.getClientCategoryTasks(o, pageable);
        model.addAttribute("add",true);
        model.addAttribute("order", o);
        model.addAttribute("clientTaskList", clientCategoryTasksModels);
        return "admin/list_of_clientTask";
    }

    @GetMapping(value = "/form")
    public String getCreateOrderForm(Model model, Authentication authentication) {
        getUserInfo(authentication);
        List<UserModel> userList = userService.getByRole("ROLE_USER");
        List<PackageModel> packageList = packageService.getAll();
        model.addAttribute("userList", userList);
        model.addAttribute("packageList", packageList);
        model.addAttribute("add", true);
        model.addAttribute("userName", user.getEmail());
        return "admin/order_form";
    }

    @PostMapping(value = "/create")
    public String addOrder(@Valid @ModelAttribute("order") OrderCreationModel orderCreationModel, Authentication authentication){
        getUserInfo(authentication);
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
