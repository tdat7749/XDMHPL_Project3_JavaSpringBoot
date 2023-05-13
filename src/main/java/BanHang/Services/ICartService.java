package BanHang.Services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import BanHang.DataBinding.CartItemDTO;
import BanHang.ViewModel.JsonResponse;
import BanHang.ViewModel.JsonResponseData;

@Service
public interface ICartService {
    JsonResponse addToCart(CartItemDTO cartItem);

    JsonResponseData<Collection<CartItemDTO>> getListCartItem();

    JsonResponseData<Collection<CartItemDTO>> remove(Integer id);

    // Collection<OrderDetailDTO> updateAmount(Integer id, Integer amount);
}
