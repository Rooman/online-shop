package com.study.shop.web.servlet;

import com.study.shop.entity.Product;
import com.study.shop.service.ProductService;
import com.study.shop.service.ServiceLocator;
import com.study.shop.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AllProductsServlet extends HttpServlet {
    private ProductService productService = ServiceLocator.getService(ProductService.class);

    public AllProductsServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> products = productService.getAll();

        HashMap<String, Object> params = new HashMap<>();
        params.put("products", products);

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("products.html", params);

        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write(page);

    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
