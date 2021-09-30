/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.service;

import com.gmail.joseifpb.etarefas.entity.Status;
import com.gmail.joseifpb.etarefas.entity.User;
import com.gmail.joseifpb.etarefas.repository.UserRepository;
import com.gmail.joseifpb.etarefas.util.ValidEmail;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author jose
 */
@Stateless
public class UserServiceImpl implements UserService {

    @EJB
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        if (!ValidEmail.isValidEmailAddressRegex(user.getEmail())) {
            return null;
        } else {
            user.setPassword(Criptografar.hashPassword(user.getPassword()));
            user.setStatus(Status.Disabled);
            return userRepository.save(user);
        }
    }

    @Override
    public User login(String email, String password) {
        User user = this.userRepository.findByEmail(email);
        System.err.println("logim ser  " + user.toString());
        if (user != null && user.getPassword().equals(Criptografar.hashPassword(password))) {
            System.out.println("com.gmail.joseifpb.etarefas.service.UserServiceImpl.login()" + user.getId());
            return user;
        } else {
            return User.fake();
        }
    }

    @Override
    public User findOn(Long id) {
        try {
            User u = this.userRepository.find(id);
            if (u == null) {
                return User.fake();
            } else {
                return u;
            }
        } catch (Exception e) {
            return User.fake();
        }
    }

    @Override
    public List<User> index() {
        
        try {
            List<User> result = this.userRepository.findAll();
            if (result.isEmpty()) {
                return Collections.EMPTY_LIST;
            } else {
                return Collections.unmodifiableList(result);
            }
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            this.userRepository.remove(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
