/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.controller;

import com.gmail.joseifpb.etarefas.entity.Status;
import com.gmail.joseifpb.etarefas.entity.User;
import com.gmail.joseifpb.etarefas.service.UserService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author jose
 */
@RequestScoped
@Named
public class UserController implements Serializable {

    @EJB
    private UserService userService;
    private User user;

    @PostConstruct
    public void init() {
        this.user = new User();
    }

    public String save() {
        try {
             System.err.println("save " + user.getName());
        this.user.setEmail("exemplo@gmail.com");
        this.user.setPasswordl("12341234");
        this.user.setStatus(Status.Active);
        this.userService.save(user);
        this.user = new User();
        } catch (Exception e) {
            System.err.println("deu erro");
             return null;
        }
        return null;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
