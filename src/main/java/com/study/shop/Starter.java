package com.study.shop;

import com.study.shop.dao.ProductDao;
import com.study.shop.dao.UserDao;
import com.study.shop.dao.jdbc.JdbcProductDao;
import com.study.shop.dao.jdbc.JdbcUserDao;
import com.study.shop.security.SecurityService;
import com.study.shop.security.entity.Session;
import com.study.shop.service.DefaultProductService;
import com.study.shop.service.DefaultUserService;
import com.study.shop.web.filter.AdminSecurityFilter;
import com.study.shop.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Starter {

    public static void main(String[] args) throws Exception {
        // dao config
        ProductDao productDao = new JdbcProductDao();
        UserDao userDao = new JdbcUserDao();

        // security
        SecurityService securityService = new SecurityService();
        List<Session> acceptedSessions = new ArrayList<>();

        // service config
        DefaultProductService defaultProductService = new DefaultProductService();
        defaultProductService.setProductDao(productDao);
        DefaultUserService defaultUserService = new DefaultUserService();
        defaultUserService.setUserDao(userDao);

        // servlet config
        AllProductsServlet allProductsServlet = new AllProductsServlet(defaultProductService);

        AddProductServlet addProductServlet = new AddProductServlet();
        addProductServlet.setProductService(defaultProductService);
        addProductServlet.setUserService(defaultUserService);
        addProductServlet.setAcceptedSessions(acceptedSessions);

        DeleteProductServlet deleteProductServlet = new DeleteProductServlet();
        deleteProductServlet.setProductService(defaultProductService);
        deleteProductServlet.setUserService(defaultUserService);
        deleteProductServlet.setAcceptedSessions(acceptedSessions);

        EditProductServlet editProductServlet = new EditProductServlet();
        editProductServlet.setProductService(defaultProductService);
        editProductServlet.setUserService(defaultUserService);
        editProductServlet.setAcceptedSessions(acceptedSessions);

        LoginServlet loginServlet = new LoginServlet(securityService);

        // server config
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allProductsServlet), "/");
        context.addServlet(new ServletHolder(allProductsServlet), "/products");
        context.addServlet(new ServletHolder(addProductServlet), "/product/add");
        context.addServlet(new ServletHolder(deleteProductServlet), "/product/delete/*");
        context.addServlet(new ServletHolder(editProductServlet), "/product/edit/*");
        context.addServlet(new ServletHolder(allProductsServlet), "/products/*");

        context.addServlet(new ServletHolder(new AssetsServlet()), "/assets/*");

        context.addServlet(new ServletHolder(loginServlet), "/login");


        AdminSecurityFilter adminSecurityFilter = new AdminSecurityFilter();
        context.addFilter(new FilterHolder(adminSecurityFilter), "/product/*", EnumSet.of(DispatcherType.REQUEST));


        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
