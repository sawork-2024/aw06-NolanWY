package pers.nolan.webpos.rest.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.nolan.webpos.mapper.DeductionMapper;
import pers.nolan.webpos.mapper.ProductMapper;
import pers.nolan.webpos.model.Deduction;
import pers.nolan.webpos.model.Product;
import pers.nolan.webpos.rest.api.ProductsApi;
import pers.nolan.webpos.rest.dto.DeductionDto;
import pers.nolan.webpos.rest.dto.ProductDto;
import pers.nolan.webpos.service.ProductService;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api")
public class ProductController implements ProductsApi {

    private final ProductService productService;

    private final ProductMapper productMapper;

    private final DeductionMapper deductionMapper;

    @Autowired
    public ProductController(ProductService productService,
                             ProductMapper productMapper,
                             DeductionMapper deductionMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.deductionMapper = deductionMapper;
    }

    @Override
    public ResponseEntity<List<ProductDto>> getProducts(String keyword, Integer category) {
        List<Product> products;
        if (keyword != null && category != null) {
            products = productService.searchProducts(keyword, category);
        } else if (keyword == null && category != null) {
            products = productService.getProductsByCategory(category);
        } else if (keyword != null) {
            products = productService.searchProducts(keyword);
        } else {
            products = productService.getProducts();
        }
        return ResponseEntity.ok(productMapper.toProductDtoList(products));
    }

    @Override
    public ResponseEntity<ProductDto> getProductById(Long productId) {
        return productService.getProductById(productId)
                             .map(productMapper::toProductDto)
                             .map(ResponseEntity::ok)
                             .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ProductDto> updateProductById(Long productId, ProductDto productDto) {
        if (!productId.equals(productDto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Product product;
        Optional<Product> optionalProduct = productService.getProductById(productId);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        product = optionalProduct.get();
        productMapper.updateProductFromDto(productDto, product);
        if (productService.updateProduct(product)) {
            return ResponseEntity.ok(productMapper.toProductDto(product));
        }
        return ResponseEntity.internalServerError().build();
    }

    @Override
    public ResponseEntity<String> deductProducts(List<DeductionDto> deductionDto) {
        List<Deduction> deductions = deductionMapper.toDeductionList(deductionDto);
        try {
            productService.deductProducts(deductions);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Deduction failed: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
