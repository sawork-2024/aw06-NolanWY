package pers.nolan.webpos.mapper;

import org.mapstruct.Mapper;
import pers.nolan.webpos.model.Order;
import pers.nolan.webpos.rest.dto.DeductionDto;

import java.util.List;

@Mapper
public interface DeductionMapper {

    DeductionDto toDeductionDto(Order order);

    List<DeductionDto> toDeductionDtoList(List<Order> orders);

}
