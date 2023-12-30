package org.example.ecommerce.repository;

import org.example.ecommerce.base.repository.BaseRepository;
import org.example.ecommerce.entity.Category;

import java.util.List;

public interface CategoryRepository extends BaseRepository<Long, Category> {

    Category getCategoryByTitle(String title);

}
