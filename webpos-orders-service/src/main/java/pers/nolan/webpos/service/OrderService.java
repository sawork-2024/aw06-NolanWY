package pers.nolan.webpos.service;

import pers.nolan.webpos.model.Order;

import java.util.List;

public interface OrderService {

    void addOrders(List<Order> orders);

}
