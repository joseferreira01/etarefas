/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.repository;

import com.gmail.joseifpb.etarefas.entity.Task;
import java.util.List;

/**
 *
 * @author jose
 */
public interface TaskRepository {

    Task find(Long id);

    List<Task> findAll();

    void remove(Long id);

    Task save(Task user);

}
