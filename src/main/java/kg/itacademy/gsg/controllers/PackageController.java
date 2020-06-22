package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.Package;
import kg.itacademy.gsg.models.PackageModel;
import kg.itacademy.gsg.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/package")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @GetMapping(value = "/list")
    public String getPackageList(@PageableDefault(3) Pageable pageable, Model model) {
        Page<PackageModel> packageList = packageService.findAll(pageable);
        model.addAttribute("packageList", packageList);
        model.addAttribute("bool", true);
        return "admin/list_of_baskets";
    }

    @GetMapping(value = "/{id}")
    public String packageInfo(@PathVariable("id") Long id, Model model) {
        Package p = packageService.getPackageById(id);
        model.addAttribute("package", p);
        return "packageDetail";
    }

    @PostMapping(value = "/create")
    public String addPackage(@Valid @ModelAttribute("package") PackageModel packageModel) {
        packageService.savePackage(packageModel);
        return "redirect:/package/list";
    }

    @PostMapping(value = "/update/{id}")
    public String updatePackage(@Valid @ModelAttribute("package") PackageModel packageModel, @PathVariable("id") Long id) {
        packageModel.setId(id);
        packageService.updatePackage(packageModel);
        return "redirect:/package/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        packageService.deletePackageById(id);
        return "redirect:/package/list";
    }
}

