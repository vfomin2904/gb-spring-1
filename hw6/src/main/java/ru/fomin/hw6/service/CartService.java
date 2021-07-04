package ru.fomin.hw6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fomin.hw6.entity.Product;
import ru.fomin.hw6.entity.User;
import ru.fomin.hw6.repository.ProductDao;
import ru.fomin.hw6.repository.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private ProductDao productDao;
    private UserDao userDao;

    @Autowired
    public CartService(ProductDao productDao, UserDao userDao) {
        this.productDao = productDao;
        this.userDao = userDao;
    }

    public List<Product> findProductByUser(Long id){
        User user = userDao.findById(id);
        if(user == null){
            return new ArrayList<>();
        }
        List<Product> products = user.getCart().stream().map(cart -> cart.getProduct())
                .collect(Collectors.toList());
        return products;
    }

    public List<User> findUserByProduct(Long id){
        Product product = productDao.findById(id);
        if(product == null){
            return new ArrayList<>();
        }
        List<User> users = product.getCart().stream().map(cart -> cart.getUser()).collect(Collectors.toList());
        return users;
    }
}
