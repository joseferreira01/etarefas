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
import com.gmail.joseifpb.etarefas.util.Message;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jose
 */
@Named
@SessionScoped
public class TaskContrller implements Serializable {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private String date;
    private String priority;
    private String responsavel;
    private Collection<SelectItem> responsavelItems;
    private Task task;
    private List<Task> tasks;
    private List<User> users;
    @EJB
    private TaskService taskService;
    @EJB
    private UserService userService;
    @Inject
    private Message message;

    @PostConstruct
    public void init() {
        this.users = new ArrayList<>();
        this.task = new Task();
    }

    public String save() {
        try {
            this.task.setStatus(TtaskStatus.Andamento);
            this.task.setPriority(Priority.valueOf(priority));
            this.task.setUsermaker(userService.findOn(userSession()));
            this.task.setDeadline(LocalDate.parse(date, formatter));
            searchResponsible();
            if (!vavidate(task)) {
                return null;
            }

            this.task = new Task();
            this.date = "";
            return "list";
        } catch (Exception e) {
            return null;
        }
    }

    public String edit(Task task) {
        this.task = task;
        this.responsavel = task.getResponsible().getName();
        this.date = task.getDateFormat();

        return "edit";
    }

    public String remove(Long task_id) {
        try {
            boolean result = this.taskService.delete(task_id, userSession());
            if (!result) {
                this.message.addMessage("Somente o criador da tarefa pode excluir-la");
            }
            return null;
        } catch (Exception e) {
            this.message.addMessage("Erro verifique os dados e tente novamente");
            return null;
        }
    }
    public String findByAttribute(){
        tasks= new ArrayList<>();
        List<Task> dd = this.taskService.findNyTaskAtribute(task);
        
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
        this.tasks = this.taskService.index();
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Long userSession() {
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

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Collection<SelectItem> getResponsavelItems() {
        this.users = this.userService.index();
        responsavelItems = new ArrayList<>();
        for (User user : users) {
            responsavelItems.add(new SelectItem(user.getName()));

        }
        return responsavelItems;
    }

    private User searchResponsible() {

        for (Iterator<User> it = users.iterator(); it.hasNext();) {
            User user = it.next();
            if (user.getName().endsWith(responsavel)) {
                this.task.setResponsible(user);
                return user;
            }
        }
        return User.fake();
    }

    public String changeTaskStatus(Long task_id) {
        try {

            this.taskService.markAsCompleted(task_id, userSession());
        } catch (Exception e) {
            this.message.addMessage("Erro tente novamente");
            return null;
        }
        return null;
    }

    private boolean vavidate(Task task) {

        try {
            if (!this.taskService.validateTask(task)) {
                this.message.addMessage("A a data de enteda não pode ser anteriora a hoje!");
                return false;
            }
            if (this.taskService.save(this.task, userSession())) {
                return true;
            } else {
                this.message.addMessage("Erro verifique os dados e tente novamente");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
