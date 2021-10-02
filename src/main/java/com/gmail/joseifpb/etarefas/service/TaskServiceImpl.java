/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.service;

import com.gmail.joseifpb.etarefas.entity.Status;
import com.gmail.joseifpb.etarefas.entity.Task;
import com.gmail.joseifpb.etarefas.entity.User;
import com.gmail.joseifpb.etarefas.repository.TaskRepository;
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
public class TaskServiceImpl implements TaskService {

    @EJB
    private TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);

    }

    @Override
    public Task findOn(Long id) {
        try {
            Task t = this.taskRepository.find(id);
            if (t == null) {
                return Task.fake();
            } else {
                return t;
            }
        } catch (Exception e) {
            return Task.fake();
        }
    }

    @Override
    public List<Task> index() {

        try {
            List<Task> result = this.taskRepository.findAll();
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
    public boolean delete(Long itask_d, Long maker_id) {
        try {
            if (maker_id.equals( findOn(itask_d).getUsermaker().getId())) {
                this.taskRepository.remove(itask_d);
                return true;
            }
            else return false;
        } catch (Exception e) {
            return false;
        }
    }

}
