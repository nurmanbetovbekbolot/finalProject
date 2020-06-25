package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Category;
import kg.itacademy.gsg.models.CategoryModel;
import kg.itacademy.gsg.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    String username;

    @GetMapping(value = "/list")
    public String getCategoryList(@PageableDefault(5) Pageable pageable, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Page<CategoryModel> categoryList = categoryService.findAll(pageable);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("userName", username);
        model.addAttribute("bool", true);
        return "admin/list_of_categories";
    }

    @GetMapping(value = "/{id}")
    public String categoryInfo(@PathVariable("id") Long id, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        model.addAttribute("add", false);
        model.addAttribute("userName", username);
        return "admin/category_form";
    }

    @GetMapping(value = "/form/{packageId}")
    public String getCategoryCreateForm(@PathVariable("packageId") Long packageId, Model model, Authentication authentication) {
        getUserInfo(authentication);
        model.addAttribute("packageId", packageId);
        model.addAttribute("add", true);
        model.addAttribute("userName", username);
        return "admin/category_form";
    }

    @PostMapping(value = "/create/{packageId}")
    public String addCategory(@Valid @ModelAttribute("category") CategoryModel categoryModel, @PathVariable("packageId") Long packageId) {
        categoryModel.setPackageId(packageId);
        categoryService.saveCategory(categoryModel);
        return "redirect:/package/" + packageId + "/category/list";
    }

    @PostMapping(value = "/update/{id}")
    public String updateCategory(@Valid @ModelAttribute("package") CategoryModel categoryModel, @PathVariable("id") Long id) {
        categoryModel.setId(id);
        categoryService.updateCategory(categoryModel);
        return "redirect:/category/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, @Param("packageId") Long packageId) {
        categoryService.deleteCategoryById(id);
        if(packageId != null)
            return "redirect:/package/"+packageId+"/category/list";

        return "redirect:/category/list";
    }

    private void getUserInfo(Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        username = userPrincipal.getUsername();
    }
}
