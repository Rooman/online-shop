package com.study.web.shop.service;

import com.study.web.shop.dao.ProductDao;
import com.study.web.shop.entity.Product;

import java.util.List;

public class DefaultProductService implements ProductService {
    private ProductDao productDao;

    public List<Product> getAll() {
        return productDao.getAll();

    }

    public Product getProduct(long id) {
        return null;
    }

    @Override
    public void deleteProduct(long id) {
        productDao.delete(id);
    }

    public void add(Product product) {
        productDao.add(product);
    }

    @Override
    public void editProduct(Product product) {
        productDao.edit(product);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

}
