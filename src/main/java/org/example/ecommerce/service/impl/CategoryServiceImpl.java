package org.example.ecommerce.service.impl;

import org.example.ecommerce.base.service.impl.BaseServiceImpl;
import org.example.ecommerce.entity.Category;
import org.example.ecommerce.repository.CategoryRepository;
import org.example.ecommerce.service.CategoryService;
import org.hibernate.Session;

import java.util.List;

public class CategoryServiceImpl extends BaseServiceImpl<Long, Category, CategoryRepository>
        implements CategoryService {

    protected final Session session;

    public CategoryServiceImpl(Session session, CategoryRepository repository) {
        super(session, repository);
        this.session = session;
    }

    @Override
    public void addCategory(Category category) {
        session.getTransaction().begin();
        repository.save(category);
        session.getTransaction().commit();
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.getAllCategories();
    }
}
