package com.study.web.shop.service;

import com.study.web.shop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product getProduct(long id);

    void deleteProduct(long id);

    void add(Product product);

    void editProduct(Product product);
}
