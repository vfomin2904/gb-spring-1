package ru.fomin.entities;

import org.springframework.beans.factory.annotation.Autowired;
import ru.fomin.repositories.ProductRepo;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private ProductRepo repository;
    private List<Product> cart = new ArrayList<>();

    @Autowired
    public Cart(ProductRepo repository) {
        this.repository = repository;
    }

    public void add(Long id){
        Product product = repository.getProductById(id);
        cart.add(product);
    }

    public void delete(Long id){
        Product product = repository.getProductById(id);
        cart.remove(product);
    }

    public void showCart(){
        cart.forEach(System.out::println);
    }


}
