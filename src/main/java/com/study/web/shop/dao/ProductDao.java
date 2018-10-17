package com.study.web.shop.dao;

import com.study.web.shop.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAll();

    Product getById(long id);

    void add(Product product);

    void delete(long id);

    void edit(Product product);
}
