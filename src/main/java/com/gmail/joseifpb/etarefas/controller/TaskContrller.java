/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.controller;

import com.gmail.joseifpb.etarefas.entity.Priority;
import com.gmail.joseifpb.etarefas.entity.Task;
import com.gmail.joseifpb.etarefas.entity.TtaskStatus;
import com.gmail.joseifpb.etarefas.entity.User;
import com.gmail.joseifpb.etarefas.service.TaskService;
import com.gmail.joseifpb.etarefas.service.UserService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author jose
 */
@Named
@RequestScoped
public class TaskContrller {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private String date;

    private Task task;
    private List<Task> tasks;
    @EJB
    private TaskService taskService;
     @EJB
    private UserService userService;

    @PostConstruct
    public void init() {
        this.task = new Task();
    }

    public String save() {
        this.task.setStatus(TtaskStatus.Andamento);
        this.task.setPriority(Priority.Low);
        this.task.setUsermaker(userService.findOn(userSession()));
        this.task.setResponsible(userService.findOn(userSession()));
        this.task.setDeadline(LocalDate.parse(date, formatter));
          
        this.taskService.save(this.task);
        this.task = new Task();
        return null;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    private Long userSession() {
        Long User = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("users");
        return User;
    }
}
