package com.study.web.shop.web.servlet;

import com.study.web.shop.entity.Product;
import com.study.web.shop.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = getProduct(request);
        productService.editProduct(product);

        response.sendRedirect("/products");
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    Product getProduct(HttpServletRequest request){
        long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.valueOf(request.getParameter("price"));
        String picturePath = request.getParameter("picturePath");

        return new Product(id, name, picturePath, price);
    }
}
