package pers.nolan.webpos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pers.nolan.webpos.model.Product;
import pers.nolan.webpos.rest.dto.ProductDto;

import java.util.List;

@Mapper(uses = CategoryMapper.class)
public interface ProductMapper {

    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDtoList(List<Product> products);

    Product toProduct(ProductDto productDto);

    void updateProductFromDto(ProductDto productDto, @MappingTarget Product product);

}
