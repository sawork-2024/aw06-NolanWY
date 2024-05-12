package pers.nolan.webpos.repository;

import org.springframework.data.repository.ListCrudRepository;
import pers.nolan.webpos.model.Category;

public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
}
