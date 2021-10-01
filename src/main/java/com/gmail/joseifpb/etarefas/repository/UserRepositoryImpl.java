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
            
            return u;
        }
        return u;
    }

    @Override
    public User find(Long id) {
        User u = em.find(User.class, id);
        return u;
    }

    @Override
    public List<User> findAll() {
      
        return em.createQuery("select u from tb_user AS u ", User.class).getResultList();

    }

    @Override
    public void remove(Long id) {
        User u = find(id);
        em.remove(u);
    }

    @Override
    public User findByEmail(String email) {
        
        Query query = em.createNativeQuery("SELECT * FROM  tb_user u  where u.email =?",User.class);
        query.setParameter(1, email);
        List<User> user = query.getResultList();
       
      
        return user.get(0);
    }

}
