package com.study.shop.web.servlet;

import com.study.shop.security.entity.Session;
import com.study.shop.service.ProductService;
import com.study.shop.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteProductServlet extends HttpServlet {
    private ProductService productService;
    private UserService userService;
    private List<Session> acceptedSessions;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] uriParts = req.getRequestURI().split("/");

        try {
            int id = Integer.parseInt(uriParts[3]);
            productService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/products");

    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setAcceptedSessions(List<Session> acceptedSessions) {
        this.acceptedSessions = acceptedSessions;
    }
}
