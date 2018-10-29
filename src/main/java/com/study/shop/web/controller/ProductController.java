package com.study.shop.web.controller;

import com.study.shop.entity.Product;
import com.study.shop.service.ProductService;
import com.study.shop.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String getAll() {
        List<Product> products = productService.getAll();

        HashMap<String, Object> params = new HashMap<>();
        params.put("products", products);

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("products.html", params);

        return page;
    }

    @ResponseBody
    @RequestMapping(path = "/product/add", method = RequestMethod.GET)
    public String addProductPage() {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("addProduct.html");

        return page;
    }

    @RequestMapping(path = "/product/add", method = RequestMethod.POST)
    public String addProduct(@RequestParam String name, @RequestParam double price, @RequestParam String picturePath) {
        int id = productService.add(name, price, picturePath);

        System.out.println("Product with id: " + id + " was created!");
        return "redirect:/products";
    }

}
