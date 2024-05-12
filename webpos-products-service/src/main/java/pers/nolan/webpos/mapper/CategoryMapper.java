package pers.nolan.webpos.mapper;

import org.mapstruct.Mapper;
import pers.nolan.webpos.model.Category;
import pers.nolan.webpos.rest.dto.CategoryDto;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDtoList(List<Category> categories);

    Category toCategory(CategoryDto categoryDto);

}
