package ru.fomin.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fomin.entities.Product;
import ru.fomin.entities.User;
import ru.fomin.repositories.ProductRepo;
import ru.fomin.repositories.UserRepo;

import java.util.List;

@Controller
@RequestMapping("/product/")
public class ProductController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/all")
    public String getAllProductsPage(Model model){
        List<Product> products = productRepo.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/{id}")
    public String getProductPage(Model model, @PathVariable Long id){
        Product product = productRepo.getById(id);
        model.addAttribute(product);
        return "product";
    }

    @PostMapping("/update")
    public String getProductPage(Product product){
        productRepo.update(product);
        logger.info("The data was successfully updated");
        return "redirect:/product/all";
    }

    @GetMapping("/add")
    public String getProductPageAdd(Model model){
        model.addAttribute("product", new Product());
        return "add_product";
    }

    @PostMapping("/add")
    public String getProductPageAdd(Product product){
        productRepo.create(product);
        logger.info("The data was created successfully");
        return "redirect:/product/all";
    }

}
