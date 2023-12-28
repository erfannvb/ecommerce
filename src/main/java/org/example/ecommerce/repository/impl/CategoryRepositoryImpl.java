package org.example.ecommerce.repository.impl;

import org.example.ecommerce.base.repository.impl.BaseRepositoryImpl;
import org.example.ecommerce.entity.Category;
import org.example.ecommerce.repository.CategoryRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryRepositoryImpl extends BaseRepositoryImpl<Long, Category> implements CategoryRepository {

    protected final Session session;

    public CategoryRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Class<Category> getEntityClass() {
        return Category.class;
    }

    @Override
    public List<Category> getAllCategories() {
        String hql = "from Category";
        Query<Category> categoryQuery = session.createQuery(hql, Category.class);
        return categoryQuery.getResultList();
    }
}
