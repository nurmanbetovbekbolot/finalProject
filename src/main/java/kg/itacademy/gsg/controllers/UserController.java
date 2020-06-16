//package kg.itacademy.gsg.controllers;
//
//import kg.itacademy.gsg.entities.User;
//import kg.itacademy.gsg.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//    @Autowired
//    UserService userService;
//
//    @GetMapping(value = "/list")
//    public String getUserList(Model model) {
//        List<User> userList = userService.getAllUsers();
//        model.addAttribute("userList", userList);
//        model.addAttribute("bool", true);
//        return "userList";
//    }
//
//    @GetMapping(value = "/{id}")
//    public String userProfile(@PathVariable("id") Long id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "userDetail";
//    }
//
//    @PostMapping(value = "/create")
//    public String addUser(@Valid @ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "redirect:/user/list";
//    }
//
//    @PostMapping(value = "/delete/{id}")
//    public String deleteById(@PathVariable("id") Long id) {
//        userService.deleteUserById(id);
//        return "redirect:/user/list";
//    }
//}