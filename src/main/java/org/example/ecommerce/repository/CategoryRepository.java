package org.example.ecommerce.repository;

import org.example.ecommerce.base.repository.BaseRepository;
import org.example.ecommerce.entity.Category;

import java.util.List;

public interface CategoryRepository extends BaseRepository<Long, Category> {

    List<Category> getAllCategories();

    Category getCategoryByTitle(String title);

}
