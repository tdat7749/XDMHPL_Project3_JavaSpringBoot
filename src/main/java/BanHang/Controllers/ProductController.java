package BanHang.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import BanHang.Services.IVegetableService;
import BanHang.ViewModel.VegetableVm;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    private final IVegetableService _vegetableService;

    public ProductController(IVegetableService vegetableService) {
        _vegetableService = vegetableService;
    }

    @RequestMapping(value = "/san-pham/{id}", method = RequestMethod.GET)
    public String Index(@PathVariable("id") Integer id, Model model) {
        VegetableVm vegetable = _vegetableService.GetVegetableVm(id);
        model.addAttribute("model", vegetable);
        return "Product/product";
    }
}
