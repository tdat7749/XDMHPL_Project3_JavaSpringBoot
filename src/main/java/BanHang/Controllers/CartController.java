package BanHang.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import BanHang.DataBinding.OrderDTO;
import BanHang.Entities.Order;
import BanHang.Services.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Controller
public class CartController {

    private final IOrderService _orderService;

    public CartController(IOrderService orderService) {
        _orderService = orderService;
    }

    @RequestMapping(value = "/gio-hang", method = RequestMethod.GET)
    public String Index() {

        return "Cart/cart";
    }

    @RequestMapping(value = "/gio-hang/luu-hoa-don", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Boolean> createOrder(@RequestBody OrderDTO order) {
        Order orderCreated = _orderService.createOrder(order);
        if (orderCreated == null) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
