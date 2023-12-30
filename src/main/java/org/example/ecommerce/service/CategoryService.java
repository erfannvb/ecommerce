package org.example.ecommerce.service;

import org.example.ecommerce.base.service.BaseService;
import org.example.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService extends BaseService<Long, Category> {

    void addCategory(Category category);

    Category getCategoryByTitle(String title);

}
