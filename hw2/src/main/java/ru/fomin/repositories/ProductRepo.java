package ru.fomin.repositories;

import ru.fomin.entities.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepo {
    private Map<Long, Product> repository = new HashMap<>();
    private AtomicLong atomicLong = new AtomicLong(0);

    public void save(Product product){
        if(product.getId() == null){
            long id = atomicLong.incrementAndGet();
            product.setId(id);
        }
        repository.put(product.getId(), product);
    }

    public void delete(Product product){
        repository.remove(product.getId());
    }

    public Map<Long, Product> getRepository() {
        return repository;
    }


    public Product getProductById(Long id){
        return repository.get(id);
    }
}
