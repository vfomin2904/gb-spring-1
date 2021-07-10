package ru.fomin.hw7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fomin.hw7.entity.Product;
import ru.fomin.hw7.entity.User;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Long>{
    List<Product> findByPriceBetween(Integer min, Integer max);

    List<Product> findByPriceGreaterThanEqual(Integer min);

    List<Product> findByPriceLessThanEqual(Integer max);
}
