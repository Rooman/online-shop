package com.study.web.shop.web.servlet;

import com.study.web.shop.entity.Product;
import com.study.web.shop.service.ProductService;
import com.study.web.shop.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllProductsServlet extends HttpServlet {
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Product> products = productService.getAll();
        Map<String, Object> params = new HashMap<>();
        params.put("products", products);

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("products.html", params);
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(page);
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
