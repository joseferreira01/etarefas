/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.service;

import com.gmail.joseifpb.etarefas.entity.Task;
import java.util.List;

/**
 *
 * @author jose
 */
public interface TaskService {

    Task save(Task task);

    Task findOn(Long id);

    List<Task> index();

    boolean delete(Long id);

}
