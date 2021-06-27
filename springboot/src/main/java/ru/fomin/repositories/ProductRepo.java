package ru.fomin.repositories;


import org.springframework.stereotype.Repository;
import ru.fomin.entities.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepo {
    private Map<Long, Product> products = new HashMap<>() ;

    @PostConstruct
    public void init(){
        products.put(1L, new Product(1L,"Orange", 100.0));
        products.put(2L, new Product(2L, "Banana", 23.0));
        products.put(3L, new Product(3L, "Apple", 12.0));
    }

    public Product getById(Long id){
        return products.get(id);
    }

    public List<Product> getAllProducts(){return new ArrayList<>(products.values());}

    public void update(Product product) {
        products.put(product.getId(), product);
    }

    public void create(Product product){
        Long id = (long)(products.size()+1);
        product.setId(id);
        products.put(id, product);
    }
}
