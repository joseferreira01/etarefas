/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.service;

import com.gmail.joseifpb.etarefas.entity.Task;
import com.gmail.joseifpb.etarefas.entity.TtaskStatus;
import com.gmail.joseifpb.etarefas.repository.TaskRepository;
import java.time.LocalDate;
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
    public boolean save(Task task, Long user_id_session) {
        if(!validateTask(task))
            return false;
        try {
            if (task.getId() == null || task.getId() < 1L) {
                taskRepository.save(task);
                return true;
            } else {
                return upTask(task, user_id_session);
            }
        } catch (Exception e) {
            return false;
        }

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
            if (maker_id.equals(findOn(itask_d).getUsermaker().getId())) {
                this.taskRepository.remove(itask_d);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private boolean upTask(Task task, Long user_id_session) {
        try {
            Task originalTask = findOn(task.getId());
            if (originalTask != null && originalTask.
                    getUsermaker().getId().
                    equals(user_id_session)) {
                this.taskRepository.save(task);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean markAsCompleted(Long task_id, Long user_id_session) {
        try {
            Task task = findOn(task_id);
            if (task != null && task.getResponsible().getId().equals(user_id_session)) {
                task.setStatus(TtaskStatus.Concluída);
                this.taskRepository.save(task);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    @Override
    public boolean validateTask(Task task) {
        boolean after = task.getDeadline().isAfter(LocalDate.now());
        System.out.println("valida data "+after);
        return after;
    }

    @Override
    public List<Task> findNyTaskAtribute(Task task) {
        return this.taskRepository.findNyTaskAtribute(task);
    }

}
