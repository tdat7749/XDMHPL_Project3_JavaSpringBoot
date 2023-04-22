package BanHang.Repository;

import org.springframework.data.repository.CrudRepository;

import BanHang.Entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findByUserNameAndPassword(String UserName, String Password);

    Customer findByCustomerId(int CustomerId);

    Customer findByUserName(String UserName);
}
