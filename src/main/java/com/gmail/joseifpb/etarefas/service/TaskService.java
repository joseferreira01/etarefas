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

    boolean save(Task task, Long user_id_session);

    Task findOn(Long id);

    List<Task> index();

    public boolean markAsCompleted(Long task_id, Long user_id_session);

    boolean delete(Long itask_d, Long maker_id);

}
