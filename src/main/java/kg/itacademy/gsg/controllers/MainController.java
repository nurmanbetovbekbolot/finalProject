package kg.itacademy.gsg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping("/")
    public String root(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_MANAGER")) {
            return "redirect:/user/list";
        }else if (request.isUserInRole("ROLE_USER")){
            return "redirect:/client/";
        }
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "authorization/login";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "redirect:/";
    }
}
