package BanHang.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import BanHang.Entities.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

}
