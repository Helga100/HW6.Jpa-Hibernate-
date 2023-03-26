package com.example.hibernate2.repository;

import com.example.hibernate2.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void addNew(User user1, User user2) {
        entityManager.merge(user1);
        entityManager.merge(user2);
    }

    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> getByEmail(String email) {
        Query getByEmail = entityManager.createQuery("SELECT u FROM User u WHERE u.email=:email");
        getByEmail.setParameter("email", email);
        return getByEmail.getResultList();
    }

    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public List<User> selectAll() {
        Query getAll = entityManager.createQuery("SELECT u FROM User u");
        return getAll.getResultList();
    }

    @Transactional
    public void delete(Long id) {
        User user = getById(id);
        if (user == null) {
            System.out.println("User is not foung");
        } else {
            entityManager.remove(user);
        }
    }
}


