package com.study.web.shop.dao.jdbc;

import com.study.web.shop.dao.ProductDao;
import com.study.web.shop.dao.jdbc.mapper.ProductRowMapper;
import com.study.web.shop.entity.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {
    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();

    private Connection connection;


    public JdbcProductDao() {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, name, picture_path, add_date, price FROM product");) {

            while (resultSet.next()) {
                products.add(PRODUCT_ROW_MAPPER.getProduct(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return products;
    }

    public Product getById(long id) {
        return null;
    }

    public void add(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product (name, picture_path, price, add_date) VALUES(?, ?, ?, ?)")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getPicturePath());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id = ?")) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void edit(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET name = ?, picture_path = ?, price = ? WHERE id = ?")) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getPicturePath());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setLong(4, product.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/shop", "postgres", "root");

    }
}
