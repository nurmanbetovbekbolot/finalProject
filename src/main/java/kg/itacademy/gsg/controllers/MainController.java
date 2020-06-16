package kg.itacademy.gsg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "authorization/login";
    }

    @GetMapping("/clients")
    public String a(){
        return "admin/list_of_clients";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}
