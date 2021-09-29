/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.repository;

import com.gmail.joseifpb.etarefas.entity.User;
import java.util.List;

/**
 *
 * @author jose
 */
public interface UserRepository {

    User find(int id);

    User findByEmail(String email);

    List<User> findAll();

    void remove(int id);

    User save(User user);

}
