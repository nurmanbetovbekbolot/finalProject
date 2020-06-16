package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Package;
import kg.itacademy.gsg.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/package")
public class PackageController {
    @Autowired
    PackageService packageService;

    @GetMapping(value = "/list")
    public String getPackageList(Model model) {
        List<Package> packageList = packageService.getAllPackages();
        model.addAttribute("packageList", packageList);
        model.addAttribute("bool", true);
        return "packageList";
    }

    @GetMapping(value = "/{id}")
    public String packageInfo(@PathVariable("id") Long id, Model model) {
        Package p = packageService.getPackageById(id);
        model.addAttribute("package", p);
        return "packageDetail";
    }

    @PostMapping(value = "/create")
    public String addTask(@Valid @ModelAttribute("package") Package p) {
        packageService.savePackage(p);
        return "redirect:/package/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        packageService.deletePackageById(id);
        return "redirect:/package/list";
    }
}

