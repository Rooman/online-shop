package com.study.web.shop;

import com.study.web.shop.dao.ProductDao;
import com.study.web.shop.dao.jdbc.JdbcProductDao;
import com.study.web.shop.service.DefaultProductService;
import com.study.web.shop.web.servlet.AddProductServlet;
import com.study.web.shop.web.servlet.AllProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Application {

    public static void main(String[] args) throws Exception {
        ProductDao productDao = new JdbcProductDao();

        DefaultProductService productService = new DefaultProductService();
        productService.setProductDao(productDao);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        AllProductsServlet allProductsServlet = new AllProductsServlet();
        allProductsServlet.setProductService(productService);
        ServletHolder mainPageServletHolder = new ServletHolder(allProductsServlet);

        context.addServlet(mainPageServletHolder, "/products");
        context.addServlet(mainPageServletHolder, "/");

        AddProductServlet addProductServlet = new AddProductServlet();
        addProductServlet.setProductService(productService);
        context.addServlet(new ServletHolder(addProductServlet), "/product/add");

        Server server = new Server(8081);
        server.setHandler(context);

        server.start();


    }
}
