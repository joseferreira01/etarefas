/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.controller;

import com.gmail.joseifpb.etarefas.entity.User;
import com.gmail.joseifpb.etarefas.service.UserService;
import com.gmail.joseifpb.etarefas.util.Message;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jose
 */
@Named
@SessionScoped
public class SessionContrler implements Serializable {

    @EJB
    private UserService userService;
    private User user;
    @Inject
    private Message message;

    @PostConstruct
    public void init() {
        this.user = new User();
    }

    public String login() {
        user = this.userService.login(user.getEmail(), user.getPassword());
        try {
            if (user.getId() > -1L) {
                FacesContext.getCurrentInstance().
                        getExternalContext().
                        getSessionMap().
                        put("users", user.getId());
                this.message.addMessage("Bem vindo " + user.getName() + "!");
                this.user = new User();

                return "faces/task/list?faces-redirect=true";
            }

        } catch (Exception e) {
            this.message.addMessage("Erro: verifique os dados e tente novamente ");
            return "faces/index?faces-redirect=true";
        }
         this.message.addMessage("Erro: verifique os dados e tente novamente ");
            return "faces/index?faces-redirect=true";
    }

    public String logout() {

        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();

        return "/faces/index?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
