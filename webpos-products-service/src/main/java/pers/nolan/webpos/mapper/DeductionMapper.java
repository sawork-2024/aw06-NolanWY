package pers.nolan.webpos.mapper;

import org.mapstruct.Mapper;
import pers.nolan.webpos.model.Deduction;
import pers.nolan.webpos.rest.dto.DeductionDto;

import java.util.List;

@Mapper
public interface DeductionMapper {

    Deduction toDeduction(DeductionDto deductionDto);

    List<Deduction> toDeductionList(List<DeductionDto> deductionDtos);

}
