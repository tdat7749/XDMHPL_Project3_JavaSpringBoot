/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanHang.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import BanHang.Entities.Category;
import BanHang.Entities.Vegetable;
import BanHang.Services.ICategoryService;
import BanHang.Services.IVegetableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author hp
 */

@Controller
public class HomeController {

    // @Autowired
    private final ICategoryService _categoryService;
    private final IVegetableService _vegetableService;

    public HomeController(ICategoryService categoryService, IVegetableService vegetableService) {
        _categoryService = categoryService;
        _vegetableService = vegetableService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Index(Model model) {
        Iterable<Category> listCategory = _categoryService.getAll();
        Iterable<Vegetable> listVegetable = _vegetableService.getAll();
        model.addAttribute("categoryModels", listCategory);
        model.addAttribute("vegetableModels", listVegetable);
        return "Home/home";
    }
}
