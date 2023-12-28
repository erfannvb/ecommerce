package org.example.ecommerce.service;

import org.example.ecommerce.base.service.BaseService;
import org.example.ecommerce.entity.Category;

public interface CategoryService extends BaseService<Long, Category> {

    void addCategory(Category category);

}
