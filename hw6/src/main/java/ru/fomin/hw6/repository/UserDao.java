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
public class UserDao {

    private EntityManagerFactory emf;

    @Autowired
    public UserDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public User findById(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            return (User)em.createQuery("select u from User u inner join fetch  u.cart c inner join fetch c.product where u.id = :id")
                    .setParameter("id", id).getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    public List<User> findAll(){
        EntityManager em = emf.createEntityManager();
        return em.createQuery("select u from User u").getResultList();
    }

    public void remove(Long id){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.createQuery("delete from User where id = :id").setParameter("id", id).executeUpdate();
        tx.commit();
    }

    public void create(User user){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
    }

    public void save(User user){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(user);
        tx.commit();
    }
}
