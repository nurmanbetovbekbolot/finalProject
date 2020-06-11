package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Category;
import kg.itacademy.gsg.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/list")
    public String getCategoryList(Model model) {
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("bool", true);
        return "categoryList";
    }

    @GetMapping(value = "/{id}")
    public String categoryInfo(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "categoryDetail";
    }

    @PostMapping(value = "/create")
    public String addCategory(@Valid @ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/category/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/category/list";
    }
}
