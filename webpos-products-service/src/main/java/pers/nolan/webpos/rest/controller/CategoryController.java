package pers.nolan.webpos.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.nolan.webpos.mapper.CategoryMapper;
import pers.nolan.webpos.rest.api.CategoriesApi;
import pers.nolan.webpos.rest.dto.CategoryDto;
import pers.nolan.webpos.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController implements CategoriesApi {

    private final ProductService productService;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(ProductService productService, CategoryMapper categoryMapper) {
        this.productService = productService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(categoryMapper.toCategoryDtoList(productService.getCategories()));
    }

}
