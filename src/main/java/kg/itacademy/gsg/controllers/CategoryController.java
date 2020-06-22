package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Category;
import kg.itacademy.gsg.models.CategoryModel;
import kg.itacademy.gsg.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/list")
    public String getCategoryList(@PageableDefault(5) Pageable pageable, Model model) {
        Page<CategoryModel> categoryList = categoryService.findAll(pageable);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("bool", true);
        return "admin/list_of_categories";
    }

    @GetMapping(value = "/{id}")
    public String categoryInfo(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "categoryDetail";
    }

    @PostMapping(value = "/create")
    public String addCategory(@Valid @ModelAttribute("category") CategoryModel categoryModel) {
        categoryService.saveCategory(categoryModel);
        return "redirect:/category/list";
    }

    @PostMapping(value = "/update/{id}")
    public String updateCategory(@Valid @ModelAttribute("package") CategoryModel categoryModel, @PathVariable("id") Long id) {
        categoryModel.setId(id);
        categoryService.updateCategory(categoryModel);
        return "redirect:/category/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/category/list";
    }
}
