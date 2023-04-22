package BanHang.Services;

import org.springframework.stereotype.Service;

import BanHang.DataBinding.OrderDTO;
import BanHang.Entities.Order;

@Service
public interface IOrderService {
    Order createOrder(OrderDTO order);
}
