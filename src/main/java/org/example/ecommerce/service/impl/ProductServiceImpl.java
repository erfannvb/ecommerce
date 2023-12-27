package org.example.ecommerce.service.impl;

import org.example.ecommerce.base.service.impl.BaseServiceImpl;
import org.example.ecommerce.entity.Product;
import org.example.ecommerce.repository.ProductRepository;
import org.example.ecommerce.service.ProductService;
import org.hibernate.Session;

public class ProductServiceImpl extends BaseServiceImpl<Long, Product, ProductRepository> implements ProductService {

    protected final Session session;

    public ProductServiceImpl(Session session, ProductRepository repository) {
        super(session, repository);
        this.session = session;
    }
}
