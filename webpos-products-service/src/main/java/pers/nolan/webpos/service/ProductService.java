package pers.nolan.webpos.service;

import pers.nolan.webpos.model.Category;
import pers.nolan.webpos.model.Deduction;
import pers.nolan.webpos.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getProducts();

    Optional<Product> getProductById(Long productId);

    List<Product> getProductsByCategory(Integer categoryId);

    List<Product> searchProducts(String keyword);

    List<Product> searchProducts(String keyword, Integer categoryId);

    boolean updateProduct(Product product);

    void deductProducts(List<Deduction> deductions);

    List<Category> getCategories();

}
