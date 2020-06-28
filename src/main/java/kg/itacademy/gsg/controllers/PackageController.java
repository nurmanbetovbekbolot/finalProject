package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Package;
import kg.itacademy.gsg.models.CategoryModel;
import kg.itacademy.gsg.models.PackageModel;
import kg.itacademy.gsg.models.TaskModel;
import kg.itacademy.gsg.services.PackageService;
import kg.itacademy.gsg.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Secured({"ROLE_ADMIN","ROLE_MANAGER"})
@RequestMapping("/package")
public class PackageController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private PackageService packageService;

    String username;

    @GetMapping(value = "/list")
    public String getPackageList(@PageableDefault(5) Pageable pageable, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Page<PackageModel> packageList = packageService.findAll(pageable);
        model.addAttribute("packageList", packageList);
        model.addAttribute("userName", username);
        model.addAttribute("bool", true);
        return "admin/list_of_packages";
    }


    @GetMapping(value = "/{id}")
    public String packageInfo(@PathVariable("id") Long id, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Package p = packageService.getPackageById(id);
        model.addAttribute("userName", username);
        model.addAttribute("package", p);
        return "admin/package_form";
    }

    @GetMapping(value = "/{packageId}/category/list")
    public String getCategoryListByPackageId(@PageableDefault(5) Pageable pageable, @PathVariable("packageId") Long packageId, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Page<CategoryModel> categoryList = packageService.getAllCategoriesByPackageId(packageId, pageable);
        model.addAttribute("userName", username);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("packageId", packageId);
        return "admin/list_of_categories";
    }


    @GetMapping(value = "/{packageId}/category/{catId}/task/list")
    public String getTaskListByCategoryId(@PageableDefault(5) Pageable pageable, @PathVariable("packageId") Long packageId, @PathVariable("catId") Long catId, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Page<TaskModel> taskList = taskService.findAllByCategoryId(catId, pageable);
        model.addAttribute("userName", username);
        model.addAttribute("taskList", taskList);
        model.addAttribute("packageId", packageId);
        model.addAttribute("catId", catId);
        return "admin/list_of_tasks";
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @GetMapping(value = "/form")
    public String getCreatePackageForm(Model model, Authentication authentication) {
        getUserInfo(authentication);
        model.addAttribute("add", true);
        model.addAttribute("userName", username);
        return "admin/package_form";
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PostMapping(value = "/create")
    public String addPackage(@Valid @ModelAttribute("package") PackageModel packageModel) {
        packageService.savePackage(packageModel);
        return "redirect:/package/list";
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PostMapping(value = "/update/{id}")
    public String updatePackage(@Valid @ModelAttribute("package") PackageModel packageModel, @PathVariable("id") Long id) {
        packageModel.setId(id);
        packageService.updatePackage(packageModel);
        return "redirect:/package/list";
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PostMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        packageService.deletePackageById(id);
        return "redirect:/package/list";
    }

    private void getUserInfo(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        username = userPrincipal.getUsername();
    }
}

