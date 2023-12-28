package org.example.ecommerce.service;

import org.example.ecommerce.base.service.BaseService;
import org.example.ecommerce.entity.Product;

public interface ProductService extends BaseService<Long, Product> {

    void addProduct(Product product);

}
