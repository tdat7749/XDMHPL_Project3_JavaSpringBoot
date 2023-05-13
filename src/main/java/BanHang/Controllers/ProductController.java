package BanHang.Controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import BanHang.DataBinding.CartItemDTO;
import BanHang.Services.ICartService;
import BanHang.Services.IVegetableService;
import BanHang.ViewModel.JsonResponse;
import BanHang.ViewModel.VegetableVm;
import lombok.var;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    private final IVegetableService _vegetableService;
    private final ICartService _cartService;

    public ProductController(IVegetableService vegetableService, ICartService cartService) {
        _vegetableService = vegetableService;
        _cartService = cartService;
    }

    @RequestMapping(value = "/san-pham/{id}", method = RequestMethod.GET)
    public String Index(@PathVariable("id") Integer id, Model model) {
        VegetableVm vegetable = _vegetableService.GetVegetableVm(id);
        model.addAttribute("model", vegetable);
        return "Product/product";
    }

    @RequestMapping(value = "/san-pham/them-gio-hang", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public ResponseEntity<JsonResponse> AddToCart(@RequestBody CartItemDTO cartItem) {
        var result = _cartService.addToCart(cartItem);
        return ResponseEntity.ok().body(result);
    }
}
