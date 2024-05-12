package pers.nolan.webpos.repository;

import org.springframework.data.repository.ListCrudRepository;
import pers.nolan.webpos.model.Order;

public interface OrderRepository extends ListCrudRepository<Order, Integer> {
}
