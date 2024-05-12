package pers.nolan.webpos.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.ListCrudRepository;
import pers.nolan.webpos.model.Product;

import java.util.List;

public interface ProductRepository extends ListCrudRepository<Product, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Product> findByIdIn(List<Long> productsIds);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByCategoryId(Integer categoryId);

    List<Product> findByNameContainingIgnoreCaseAndCategoryId(String keyword, Integer categoryId);

}
