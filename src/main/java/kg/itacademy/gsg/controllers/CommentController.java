package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.ClientTasks;
import kg.itacademy.gsg.entities.Comment;
import kg.itacademy.gsg.entities.User;
import kg.itacademy.gsg.models.CommentModel;
import kg.itacademy.gsg.services.ClientTasksService;
import kg.itacademy.gsg.services.CommentService;
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
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ClientTasksService clientTasksService;

    @Autowired
    private UserService userService;

    private User user;

    @GetMapping(value = "/{id}/list")
    public String getCommentList(@PathVariable("id") Long id, @PageableDefault(9) Pageable pageable, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Page<CommentModel> commentModels = commentService.getAllCommentsByTask(id, pageable);
        model.addAttribute("commentList", commentModels);
        model.addAttribute("taskId", id);
        model.addAttribute("userName", user.getEmail());
        return "admin/list_of_comments";
    }

    @PostMapping(value = "/{id}/create")
    public String addComment(@PathVariable("id") Long id, @Valid @ModelAttribute("comment") Comment comment, Authentication authentication) {
        getUserInfo(authentication);
        comment.setUser(user);
        comment.setClientTask(clientTasksService.getById(id));
        commentService.saveComment(comment);
        return "redirect:/comment/"+id+"/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        commentService.deleteCommentById(id);
        return "redirect:/comment/list";
    }


    private void getUserInfo(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        user = userService.findByEmail(userPrincipal.getUsername());
    }
}