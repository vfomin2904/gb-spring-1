package ru.fomin.hw6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fomin.hw6.service.CartService;

import javax.annotation.PostConstruct;

@Component
public class Main {

    private CartService cartService;

    @Autowired
    public Main(CartService cartService) {
        this.cartService = cartService;
    }

    @PostConstruct
    public void homework(){
        System.out.println(cartService.findProductByUser(2L));
        System.out.println(cartService.findUserByProduct(10L));
    }
}
