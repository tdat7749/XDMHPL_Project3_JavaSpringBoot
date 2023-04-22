package BanHang.Services;

import org.springframework.stereotype.Service;

import BanHang.DataBinding.RegisterDTO;
import BanHang.Entities.Customer;

@Service
public interface ICustomerService {
    public Customer Login(String UserName, String Password);

    public Integer Register(RegisterDTO customer);
}
