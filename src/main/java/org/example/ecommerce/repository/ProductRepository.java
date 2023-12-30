package org.example.ecommerce.repository;

import org.example.ecommerce.base.repository.BaseRepository;
import org.example.ecommerce.entity.Product;

import java.util.List;

public interface ProductRepository extends BaseRepository<Long, Product> {

    List<Product> getAllProductByCategoryId(long id);

}
