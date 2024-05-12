package pers.nolan.webpos.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.nolan.webpos.mapper.OrderMapper;
import pers.nolan.webpos.model.Order;
import pers.nolan.webpos.rest.api.OrdersApi;
import pers.nolan.webpos.rest.dto.OrderDto;
import pers.nolan.webpos.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController implements OrdersApi {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Override
    public ResponseEntity<String> addOrders(List<OrderDto> orderDto) {
        List<Order> orders = orderMapper.toOrderList(orderDto);
        try {
            orderService.addOrders(orders);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
