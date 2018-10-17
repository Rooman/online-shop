package com.study.web.shop.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product {
    private long id;
    private String name;
    private String picturePath;
    private LocalDateTime addDate;
    private double price;

    public Product(long id, String name, String picturePath, LocalDateTime addDate, double price) {
        this.id = id;
        this.name = name;
        this.picturePath = picturePath;
        this.addDate = addDate;
        this.price = price;
    }

    public Product(long id, String name, String picturePath, double price) {
        this.id = id;
        this.name = name;
        this.picturePath = picturePath;
        this.price = price;
    }

    public Product(String name, String picturePath, double price) {
        this.name = name;
        this.picturePath = picturePath;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public LocalDateTime getAddDate() {
        return addDate;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
