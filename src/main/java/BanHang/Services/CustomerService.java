package BanHang.Services;

import org.springframework.stereotype.Service;

import BanHang.DataBinding.RegisterDTO;
import BanHang.Entities.Customer;
import BanHang.Repository.CustomerRepository;
import jakarta.transaction.Transactional;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository _customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        _customerRepository = customerRepository;
    }

    public Customer Login(String UserName, String Password) {
        return _customerRepository.findByUserNameAndPassword(UserName, Password);
    }

    @Transactional
    public Integer Register(RegisterDTO customer) {

        Customer checkCustomer = _customerRepository.findByUserName(customer.getUserName());
        if (checkCustomer != null) {
            return 0; // Đã tồn tại tài khoản này
            // Nếu về sau có Email hay gì đó cần check thì return < 0 VD: -1 -2 -3. Để
            // handle lỗi ở Controller
        }
        Customer newCustomer = new Customer();
        newCustomer.setAddress(customer.getAddress());
        newCustomer.setCity(customer.getCity());
        newCustomer.setFullName(customer.getFullName());
        newCustomer.setPassword(customer.getPassword());
        newCustomer.setUserName(customer.getUserName());
        _customerRepository.save(newCustomer);
        return 1;
    }
}
