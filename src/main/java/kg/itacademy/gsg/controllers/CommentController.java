package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Comment;
import kg.itacademy.gsg.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/list")
    public String getCommentList(Model model) {
        List<Comment> commentList = commentService.getAllComments();
        model.addAttribute("commentList", commentList);
        model.addAttribute("bool", true);
        return "commentList";
    }

    @GetMapping(value = "/{id}")
    public String commentInfo(@PathVariable("id") Long id, Model model) {
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "commentDetail";
    }

    @PostMapping(value = "/create")
    public String addComment(@Valid @ModelAttribute("comment") Comment comment) {
        commentService.saveComment(comment);
        return "redirect:/comment/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        commentService.deleteCommentById(id);
        return "redirect:/comment/list";
    }
}