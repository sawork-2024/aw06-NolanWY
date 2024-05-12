package pers.nolan.webpos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pers.nolan.webpos.model.Order;
import pers.nolan.webpos.rest.dto.OrderDto;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    Order toOrder(OrderDto orderDto);

    List<Order> toOrderList(List<OrderDto> orderDtos);

}
