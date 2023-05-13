package BanHang.Services;

import BanHang.Repository.CustomerRepository;
import BanHang.Repository.OrderRepository;
import BanHang.Repository.VegetableRepository;
import BanHang.ViewModel.JsonResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import BanHang.DataBinding.CartItemDTO;
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
    private final HttpServletRequest _request;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
            VegetableRepository vegetableRepository, IVegetableService vegetableService, HttpServletRequest request) {
        _orderRepository = orderRepository;
        _customerRepository = customerRepository;
        _vegetableRepository = vegetableRepository;
        _vegetableService = vegetableService;
        _request = request;
    }

    @Transactional
    public JsonResponse createOrder(OrderDTO order) {
        // Get khách hàng
        Customer customer = _customerRepository.findByCustomerId(order.getCustomerId());

        if (customer == null) {
            return new JsonResponse(false, "Bạn chưa đăng nhập hoặc có lỗi");
        }

        // Get giỏ hàng trong session
        HttpSession session = _request.getSession();

        HashMap<Integer, CartItemDTO> cartItem = new HashMap<>();
        cartItem = (HashMap<Integer, CartItemDTO>) session.getAttribute("listItem");

        if (cartItem == null || cartItem.isEmpty()) {
            return new JsonResponse(false, "Giỏ hàng trống");
        }

        // Convert từ CartItemDTO sang OrderDetailDTO
        Collection<OrderDetailDTO> listOrderDetail = new ArrayList<>();

        for (CartItemDTO item : cartItem.values()) {
            OrderDetailDTO detail = new OrderDetailDTO();
            detail.setVegetableId(item.getVegetableId());
            detail.setPrice(item.getPrice());
            detail.setSubTotal(item.getSubTotal());
            detail.setQuantity(item.getQuantity());

            listOrderDetail.add(detail);
        }

        // Tạo hóa đơn
        Order newOrder = new Order();

        for (OrderDetailDTO item : listOrderDetail) {
            OrderDetail detail = new OrderDetail();
            Vegetable vegetable = _vegetableRepository.findById(item.getVegetableId()).orElse(null);
            detail.setPrice(item.getPrice());
            detail.setQuantity(item.getQuantity());
            detail.setSubTotal(item.getSubTotal());
            detail.setVegetable(vegetable);

            // hàm này dùng để kết nối chéo giữa hóa đơn và chi tiết hóa đơn
            newOrder.addToOrderDetail(detail);

            // set total
            newOrder.setTotal(newOrder.getTotal() + item.getSubTotal());
        }
        newOrder.setCustomer(customer);
        newOrder.setAddress(order.getAddress());
        newOrder.setNote(order.getNote());
        newOrder.setDate(new Date());

        Order checkOrder = _orderRepository.save(newOrder);

        if (checkOrder != null) {
            _vegetableService.UpdateAmount(listOrderDetail);
        } else {
            new JsonResponse(false, "Đặt hàng thất bại");
        }

        // set lại session giỏ hàng trống
        session.setAttribute("listItem", new HashMap<Integer, CartItemDTO>());
        return new JsonResponse(true, "Đặt hàng thành công");
    }
}
