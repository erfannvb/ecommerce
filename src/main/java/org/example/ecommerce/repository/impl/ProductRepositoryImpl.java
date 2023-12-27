package org.example.ecommerce.repository.impl;

import org.example.ecommerce.base.repository.impl.BaseRepositoryImpl;
import org.example.ecommerce.entity.Product;
import org.example.ecommerce.repository.ProductRepository;
import org.hibernate.Session;

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
}
