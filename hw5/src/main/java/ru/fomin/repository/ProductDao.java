package ru.fomin.repository;

import org.hibernate.cfg.Configuration;
import ru.fomin.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ProductDao {

    private EntityManagerFactory entityManagerFactory;

    public ProductDao(){
        entityManagerFactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public Product findById(Long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Product product = em.createNamedQuery("findById", Product.class).setParameter("id", id).getSingleResult();
        tx.commit();
        em.close();
        return product;
    }

    public List<Product> findAll(){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        List<Product> products = em.createQuery("SELECT p FROM Product  p", Product.class).getResultList();
        tx.commit();
        em.close();
        return products;
    }

    public void deleteById(Long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Product product = em.createNamedQuery("findById", Product.class).setParameter("id", id).getSingleResult();
        em.remove(product);
        tx.commit();
        em.close();
    }

    public void saveOrUpdate(Product product){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(product);
        tx.commit();
        em.close();
    }

}
