package com.study.shop.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HelloWorldController {


    @RequestMapping(method = RequestMethod.GET, path = "/hello/{id}")
    public void hello(HttpServletResponse response, @PathVariable int id) throws IOException {
        response.getWriter().write("Hello from controller " + id);
    }
}
