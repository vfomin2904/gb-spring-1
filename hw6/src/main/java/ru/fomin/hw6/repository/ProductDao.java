package ru.fomin.hw6.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.fomin.hw6.entity.Product;
import ru.fomin.hw6.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class ProductDao {


    private EntityManagerFactory emf;

    @Autowired
    public ProductDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Product findById(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            return (Product)em.createQuery("select p from Product p inner join fetch  p.cart c inner join fetch c.product where p.id = :id")
                    .setParameter("id", id).getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    public List<Product> findAll(){
        EntityManager em = emf.createEntityManager();
        return em.createQuery("select p from Product p").getResultList();
    }

    public void remove(Long id){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.createQuery("delete from Product where id = :id").setParameter("id", id).executeUpdate();
        tx.commit();
    }

    public void create(Product product){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(product);
        tx.commit();
    }

    public void save(Product product){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(product);
        tx.commit();
    }
}
