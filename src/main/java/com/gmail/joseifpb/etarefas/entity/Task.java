/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author jose
 */
@Entity
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(length = 140)
    private String description;
    @ManyToOne
    @JoinColumn(name = "maker_code",nullable = false)
    private User usermaker;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TtaskStatus status;
    @Column(nullable = false)
    private LocalDate deadline;
    @ManyToOne
    @JoinColumn(name = "responsible_code")
    private User responsible;

    public Task() {
    }

    private Task(Long id, String title) {
        this.id = id;
        this.title = title;

    }

    public static Task fake() {
        return new Task(-1L, "tarefa fake");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUsermaker() {
        return usermaker;
    }

    public void setUsermaker(User usermaker) {
        this.usermaker = usermaker;
    }

    public TtaskStatus getStatus() {
        return status;
    }

    public void setStatus(TtaskStatus status) {
        this.status = status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", priority=" + priority + ", title=" + title + ", description=" + description + ", maker=" + usermaker.getName() + ", status=" + status + ", deadline=" + deadline + ", responsible=" + responsible.getName() + '}';
    }

}
