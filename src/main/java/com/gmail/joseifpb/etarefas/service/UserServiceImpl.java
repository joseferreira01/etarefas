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
        System.err.println("logim ser  "+user.toString());
         if (user != null && user.getPassword().equals( Criptografar.hashPassword(password))) {
             System.out.println("com.gmail.joseifpb.etarefas.service.UserServiceImpl.login()"+user.getId());
            return user;
        } else {
            return User.fake();
        }
    }
    

}
