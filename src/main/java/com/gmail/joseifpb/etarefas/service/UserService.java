/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.service;

import com.gmail.joseifpb.etarefas.entity.User;

/**
 *
 * @author jose
 */
public interface UserService {

    User save(User user);

    User login(String email, String password);

}
