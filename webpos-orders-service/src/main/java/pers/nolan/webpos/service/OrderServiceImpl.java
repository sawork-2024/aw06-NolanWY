package pers.nolan.webpos.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pers.nolan.webpos.mapper.DeductionMapper;
import pers.nolan.webpos.model.Order;
import pers.nolan.webpos.repository.OrderRepository;
import pers.nolan.webpos.rest.dto.DeductionDto;

import java.util.List;

@Log4j2
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final DeductionMapper deductionMapper;

    @LoadBalanced
    private final RestTemplate restTemplate;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            DeductionMapper deductionMapper,
                            RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.deductionMapper = deductionMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    @GlobalTransactional
    @Transactional
    public void addOrders(List<Order> orders) {
        List<DeductionDto> deductionDtoList = deductionMapper.toDeductionDtoList(orders);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            request = new HttpEntity<>(objectMapper.writeValueAsString(deductionDtoList), headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://products-service/api/products/deduction", request, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            orderRepository.saveAll(orders);
        }
    }
}
