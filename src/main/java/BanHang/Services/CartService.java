package BanHang.Services;

import BanHang.DataBinding.CartItemDTO;
import BanHang.Repository.VegetableRepository;
import BanHang.ViewModel.JsonResponse;
import BanHang.ViewModel.JsonResponseData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.var;

import java.util.Collection;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CartService implements ICartService {

    private final HttpServletRequest _request;
    private final VegetableRepository _vegetableRepository;

    public CartService(HttpServletRequest request, VegetableRepository vegetableRepository) {
        _request = request;
        _vegetableRepository = vegetableRepository;
    }

    public JsonResponse addToCart(CartItemDTO cartItem) {
        // Get sản phẩm mới thêm vào
        var product = _vegetableRepository.findById(cartItem.getVegetableId()).orElse(null);

        HttpSession session = _request.getSession();
        HashMap<Integer, CartItemDTO> listItem = new HashMap<>();
        if (session.getAttribute("listItem") == null) {
            session.setAttribute("listItem", listItem);
        }

        listItem = (HashMap<Integer, CartItemDTO>) session.getAttribute("listItem");

        CartItemDTO item = listItem.get(cartItem.getVegetableId());
        if (item == null) {
            listItem.put(cartItem.getVegetableId(), cartItem);
        } else {
            if (item.getQuantity() + cartItem.getQuantity() > product.getAmount()) {
                return new JsonResponse(false, "Số lượng sản phẩm đã vượt quá số lượng còn lại");
            }
            item.setQuantity(item.getQuantity() + cartItem.getQuantity());
            item.setSubTotal(item.getSubTotal() + cartItem.getSubTotal());
        }
        session.setAttribute("listItem", listItem);
        return new JsonResponse(true, "Thêm sản phẩm vào giỏ hàng thành công");
    }

    public JsonResponseData<Collection<CartItemDTO>> getListCartItem() {
        HttpSession session = _request.getSession();
        HashMap<Integer, CartItemDTO> listItem = new HashMap<>();
        if (session.getAttribute("listItem") == null) {
            session.setAttribute("listItem", listItem);
            return new JsonResponseData<Collection<CartItemDTO>>(true, "Success", listItem.values());
        }

        listItem = (HashMap<Integer, CartItemDTO>) session.getAttribute("listItem");

        return new JsonResponseData<Collection<CartItemDTO>>(true, "Success", listItem.values());
    }

    public JsonResponseData<Collection<CartItemDTO>> remove(Integer id) {
        HttpSession session = _request.getSession();
        HashMap<Integer, CartItemDTO> listItem = new HashMap<>();

        listItem = (HashMap<Integer, CartItemDTO>) session.getAttribute("listItem");

        CartItemDTO item = listItem.get(id);
        if (item == null) {
            return new JsonResponseData<Collection<CartItemDTO>>(false,
                    "Không tồn tại sản phẩm này, vui lòng thử lại sau", listItem.values());
        }
        Boolean result = listItem.remove(id, item);
        if (result == false) {
            return new JsonResponseData<Collection<CartItemDTO>>(false, "Xóa thất bại, vui lòng thử lại sau",
                    listItem.values());
        }
        session.setAttribute("listItem", listItem);
        return new JsonResponseData<Collection<CartItemDTO>>(true, "Xóa thành công", listItem.values());
    }

}
