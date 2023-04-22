package BanHang.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import BanHang.DataBinding.FilterVegetableDTO;
import BanHang.Entities.Vegetable;
import BanHang.Entities.Category;
import java.util.List;

import BanHang.Services.ICategoryService;
import BanHang.Services.IVegetableService;

@Controller
public class ShopController {

    private final IVegetableService _vegetableService;
    private final ICategoryService _categoryService;

    public ShopController(IVegetableService vegetableService, ICategoryService categoryService) {
        _vegetableService = vegetableService;
        _categoryService = categoryService;
    }

    @RequestMapping(value = "/cua-hang", method = RequestMethod.GET)
    public String Index(Model model, @RequestParam(name = "catid", required = false) Integer catId,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "sort-by", required = false) String sort) {

        FilterVegetableDTO filters = new FilterVegetableDTO();
        filters.setCatId(catId);
        filters.setKeyword(keyword);
        filters.setSort(sort);

        List<Vegetable> listVegetable = _vegetableService.GetFilterVegetable(filters);
        Iterable<Category> listCategory = _categoryService.getAll();

        model.addAttribute("models", listVegetable);
        model.addAttribute("categories", listCategory);
        return "Shop/shop";
    }
}
