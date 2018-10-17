package com.study.web.shop.dao.jdbc.mapper;

import com.study.web.shop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ProductRowMapper implements RowMapper<Product> {

    public Product mapRow(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String picturePath = resultSet.getString("picture_path");
        LocalDateTime addDate = resultSet.getTimestamp("add_date").toLocalDateTime();
        double price = resultSet.getDouble("price");

        return new Product(id, name, picturePath, addDate, price);


    }
}
