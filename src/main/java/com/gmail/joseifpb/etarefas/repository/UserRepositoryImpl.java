/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.repository;

import com.gmail.joseifpb.etarefas.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jose
 */
@Stateless
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext(unitName = "etarefas_UP")
    private EntityManager em;

    @Override
    public User save(User user) {
        User u = User.fake();
        try {
            u = em.merge(user);
        } catch (Exception e) {
            System.out.println("erro");

            return User.fake();
        }
        return u;
    }

    @Override
    public User find(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        System.err.println("to no list repo");
        return em.createQuery("select u from User ", User.class).getResultList();

    }

    @Override
    public void remove(int id) {
        User u = find(id);
        em.remove(u);
    }

    @Override
    public User findByEmail(String email) {
        Query query = em.createQuery("SELECT * FROM u User whare u.email = email");
        query.setParameter("email", email);
        User user = (User) query.getSingleResult();
        return user;
    }

}