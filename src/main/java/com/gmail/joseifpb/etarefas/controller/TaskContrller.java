/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.controller;

import com.gmail.joseifpb.etarefas.entity.Priority;
import com.gmail.joseifpb.etarefas.entity.Task;
import com.gmail.joseifpb.etarefas.entity.TtaskStatus;
import com.gmail.joseifpb.etarefas.service.TaskService;
import com.gmail.joseifpb.etarefas.service.UserService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
    private String priority;

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
        System.out.println(priority);
        this.task.setPriority(Priority.valueOf(priority));
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
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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
     private SelectItem[] prioridades = {
    new SelectItem(Priority.High),
    new SelectItem(Priority.Low),
    new SelectItem(Priority.Medium)
  };

    public void setPrioridades(SelectItem[] prioridades) {
        this.prioridades = prioridades;
    }

    public SelectItem[] getPrioridades() {
        return prioridades;
    }
     
}
