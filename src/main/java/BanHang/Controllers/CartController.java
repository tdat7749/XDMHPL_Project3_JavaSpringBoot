package BanHang.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import BanHang.DataBinding.CartItemDTO;
import BanHang.DataBinding.OrderDTO;
import BanHang.Entities.Order;
import BanHang.Services.ICartService;
import BanHang.Services.IOrderService;
import BanHang.ViewModel.JsonResponse;
import BanHang.ViewModel.JsonResponseData;
import lombok.var;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Controller
public class CartController {

    private final IOrderService _orderService;
    private final ICartService _cartService;

    public CartController(IOrderService orderService, ICartService cartService) {
        _orderService = orderService;
        _cartService = cartService;
    }

    @RequestMapping(value = "/gio-hang", method = RequestMethod.GET)
    public String Index() {

        return "Cart/cart";
    }

    @RequestMapping(value = "/gio-hang", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonResponseData<Collection<CartItemDTO>>> GetCartItem() {

        var result = _cartService.getListCartItem();
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/gio-hang/xoa-san-pham", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<JsonResponse> DeleteCartItem(@RequestBody Integer id) {
        var result = _cartService.remove(id);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/gio-hang/luu-hoa-don", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<JsonResponse> createOrder(@RequestBody OrderDTO order) {
        var result = _orderService.createOrder(order);

        return ResponseEntity.ok().body(result);
    }

}
