package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.User;
import kg.itacademy.gsg.entities.UserRole;
import kg.itacademy.gsg.models.UserModel;
import kg.itacademy.gsg.services.UserService;
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

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    String username;

    @GetMapping(value = "/list")
    public String getUserListForAdmin(@PageableDefault(5) Pageable pageable,Model model, Authentication authentication) {
        getUserInfo(authentication);
        Page<UserModel> userList = userService.findAll(pageable);
        model.addAttribute("userList", userList);
        model.addAttribute("userName", username);
        model.addAttribute("bool", true);
        return "admin/list_of_users";
    }
//
//    @GetMapping(value = "/list/forManager")
//    public String getUserListForManager(@PageableDefault(5) Pageable pageable,Model model, Authentication authentication) {
//        getUserInfo(authentication);
//        Page<UserModel> userList = userService.findAll(pageable);
//        model.addAttribute("userList", userList);
//        model.addAttribute("userName", username);
//        model.addAttribute("bool", true);
//        return "admin/list_of_users";
//    }

    @GetMapping(value = "/form")
    public String getCreateUserForm(Model model, Authentication authentication) {
        getUserInfo(authentication);
        model.addAttribute("add", true);
        model.addAttribute("userName", username);
        return "admin/user_form";
    }


    @GetMapping(value = "/{id}")
    public String userProfile(@PathVariable("id") Long id, Model model, Authentication authentication) {
        getUserInfo(authentication);
        User user = userService.getUserById(id);
        model.addAttribute("add", false);
        model.addAttribute("user", user);
        model.addAttribute("userName", username);
        return "admin/user_form";
    }

    @PostMapping(value = "/create")
    public String addUser(@Valid @ModelAttribute("user") UserModel userModel, @Valid @ModelAttribute("userRole") UserRole userRole) {
        userModel.setRole(userRole);
        userService.saveUser(userModel);
        return "redirect:/user/list";
    }

    @PostMapping(value = "/update/{id}")
    public String updateUser(@Valid @ModelAttribute("user") UserModel userModel, @PathVariable("id") Long id) {
        userModel.setId(id);
        userService.updateUser(userModel);
        return "redirect:/user/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/user/list";
    }

    private void getUserInfo(Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        username = userPrincipal.getUsername();
    }
}