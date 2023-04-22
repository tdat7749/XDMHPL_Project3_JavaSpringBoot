package BanHang.Services;

import BanHang.Repository.CustomerRepository;
import BanHang.Repository.OrderRepository;
import BanHang.Repository.VegetableRepository;
import jakarta.transaction.Transactional;

import java.util.Date;

import org.springframework.stereotype.Service;

import BanHang.DataBinding.OrderDTO;
import BanHang.DataBinding.OrderDetailDTO;
import BanHang.Entities.Order;
import BanHang.Entities.Customer;
import BanHang.Entities.OrderDetail;
import BanHang.Entities.Vegetable;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository _orderRepository;
    private final CustomerRepository _customerRepository;
    private final VegetableRepository _vegetableRepository;
    private final IVegetableService _vegetableService;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
            VegetableRepository vegetableRepository, IVegetableService vegetableService) {
        _orderRepository = orderRepository;
        _customerRepository = customerRepository;
        _vegetableRepository = vegetableRepository;
        _vegetableService = vegetableService;
    }

    @Transactional
    public Order createOrder(OrderDTO order) {

        Customer customer = _customerRepository.findByCustomerId(order.getCustomerId());

        Order newOrder = new Order();

        for (OrderDetailDTO item : order.getOrderDetailDtos()) {
            OrderDetail detail = new OrderDetail();
            Vegetable vegetable = _vegetableRepository.findById(item.getVegetableId()).orElse(null);
            detail.setPrice(item.getPrice());
            detail.setQuantity(item.getQuantity());
            detail.setSubTotal(item.getSubTotal());
            detail.setVegetable(vegetable);

            // hàm này dùng để kết nối chéo giữa hóa đơn và chi tiết hóa đơn
            newOrder.addToOrderDetail(detail);
        }
        newOrder.setCustomer(customer);
        newOrder.setAddress(order.getAddress());
        newOrder.setNote(order.getNote());
        newOrder.setTotal(order.getTotal());
        newOrder.setDate(new Date());

        Order checkOrder = _orderRepository.save(newOrder);

        if (checkOrder != null) {
            _vegetableService.UpdateAmount(order.getOrderDetailDtos());
        }

        return _orderRepository.save(newOrder);
    }
}
