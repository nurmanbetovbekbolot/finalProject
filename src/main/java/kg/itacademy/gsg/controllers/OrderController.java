package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Order;
import kg.itacademy.gsg.models.OrderModel;
import kg.itacademy.gsg.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/list")
    public String getOrderList(@PageableDefault(3) Pageable pageable, Model model) {
        Page<OrderModel> orderList = orderService.findAll(pageable);
        model.addAttribute("orderList", orderList);
        model.addAttribute("bool", true);
        return "admin/list_of_orders";
    }

    @GetMapping(value = "/{id}")
    public String orderInfo(@PathVariable("id") Long id, Model model) {
        Order o = orderService.getOrderById(id);
        model.addAttribute("order", o);
        return "orderDetail";
    }

    @PostMapping(value = "/create")
    public String addOrder(@Valid @ModelAttribute("order") OrderModel orderModel) {
        orderService.saveOrder(orderModel);
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
}
