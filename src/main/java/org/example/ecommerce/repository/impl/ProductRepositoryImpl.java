package org.example.ecommerce.repository.impl;

import org.example.ecommerce.base.repository.impl.BaseRepositoryImpl;
import org.example.ecommerce.entity.Product;
import org.example.ecommerce.repository.ProductRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductRepositoryImpl extends BaseRepositoryImpl<Long, Product> implements ProductRepository {

    protected final Session session;

    public ProductRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    public List<Product> getAllProductByCategoryId(long id) {
        String hql = "from Product p where p.category.id =: id";
        Query<Product> productQuery = session.createQuery(hql, Product.class);
        productQuery.setParameter("id", id);
        return productQuery.getResultList();
    }
}
