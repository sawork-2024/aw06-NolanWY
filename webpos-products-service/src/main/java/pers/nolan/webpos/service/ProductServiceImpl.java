package pers.nolan.webpos.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.nolan.webpos.model.Category;
import pers.nolan.webpos.model.Deduction;
import pers.nolan.webpos.model.Product;
import pers.nolan.webpos.repository.CategoryRepository;
import pers.nolan.webpos.repository.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Cacheable("products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    @Cacheable("products")
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> getProductsByCategory(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Product> searchProducts(String keyword, Integer categoryId) {
        return productRepository.findByNameContainingIgnoreCaseAndCategoryId(keyword, categoryId);
    }

    @Override
    @CacheEvict(value = "products", key = "#product.id")
    public boolean updateProduct(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    @Transactional
    public void deductProducts(List<Deduction> deductions) {
        List<Product> products = productRepository.findByIdIn(deductions.stream().map(Deduction::getProductId).toList());
        Map<Long, Product> id2Product = products.stream()
                                                .collect(Collectors.toMap(Product::getId, p -> p));
        for (Deduction deduction : deductions) {
            Product product = id2Product.get(deduction.getProductId());
            if (product == null || !deduction.getProductId().equals(product.getId())) {
                throw new IllegalArgumentException("Illegal product id " + deduction.getProductId());
            }
            if (!product.getAvailable()) {
                throw new IllegalArgumentException(String.format("Product %d is unavailable", product.getId()));
            }
            if (product.getStock() < deduction.getQuantity()) {
                throw new IllegalArgumentException(String.format("Cannot deduct %d product %d with stock %d",
                        deduction.getQuantity(), product.getId(), product.getStock()));
            }
            product.setStock(product.getStock() - deduction.getQuantity());
        }
        productRepository.saveAll(products);
    }

    @Override
    @Cacheable("categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

}
