package ru.fomin;

import ru.fomin.entity.Product;
import ru.fomin.repository.ProductDao;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        List.of(
                new Product(null, "Orange", 10),
                new Product(null, "Banana", 20),
                new Product(null, "Apple", 30),
                new Product(null, "Cherry", 40))
                .stream()
                .forEach(p -> productDao.saveOrUpdate(p));

        List<Product> all = productDao.findAll();
        all.stream().forEach(System.out::println);


        Product product = productDao.findById(10L);
        System.out.println(product);

        productDao.deleteById(12L);

        List<Product> all1 = productDao.findAll();
        all1.stream().forEach(System.out::println);
    }
}
