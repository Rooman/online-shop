package com.study.web.shop.web.servlet;

import com.study.web.shop.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    List<String> acceptedTokens;

    //   UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("login.html");

        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Object user = new Object();


        if (user != null) {
            String token = UUID.randomUUID().toString();
            acceptedTokens.add(token);
            Cookie cookie = new Cookie("user-token", token);
            resp.addCookie(cookie);
            // auth
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        System.out.println(name + " " + password);
    }

    public void setAcceptedTokens(List<String> acceptedTokens) {
        this.acceptedTokens = acceptedTokens;
    }
}
