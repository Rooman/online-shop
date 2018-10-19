package com.study.shop.web.servlet;

import com.study.shop.security.SecurityService;
import com.study.shop.security.entity.Session;
import com.study.shop.web.templater.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private SecurityService securityService;

    public LoginServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();

        HashMap<String, Object> params = new HashMap<>();
        params.put("message", "");
        String page = pageGenerator.getPage("login.html", params);

        resp.getWriter().write(page); // page > buffer on outputStream
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Session session = securityService.auth(name, password);

        if (session != null) {
            Cookie cookie = new Cookie("user-session", session.getToken());
            resp.addCookie(cookie);
            resp.sendRedirect("/");
        } else {
            resp.sendRedirect("/login");
        }
    }

}
