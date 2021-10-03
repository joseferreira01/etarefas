/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.repository;

import com.gmail.joseifpb.etarefas.entity.Task;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jose
 */
@Stateless
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext(unitName = "etarefas_UP")
    private EntityManager em;

    @Override
    public Task save(Task task) {
        Task t = Task.fake();
        try {
            t = em.merge(task);
        } catch (Exception e) {

            return t;
        }
        return t;
    }

    @Override
    public Task find(Long id) {
        try {
            return em.find(Task.class, id);
        } catch (Exception e) {
            return Task.fake();
        }
    }

    @Override
    public List<Task> findAll() {

        return em.createQuery("select t from Task AS t ", Task.class).getResultList();

    }

    @Override
    public void remove(Long id) {
        Task t = find(id);
        em.remove(t);
    }

    @Override
    public List<Task> findNyTaskAtribute(Task task) {
        System.out.println("meu ti "+task.getTitle());
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

            CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
            Root<Task> root = criteriaQuery.from(Task.class);

            CriteriaQuery<Task> select = criteriaQuery.select(root);
            TypedQuery<Task> query = em.createQuery(select);
            List<Task> results = query.getResultList(); 
            for (Task result : results) {
                System.out.println("resut q "+result.getTitle());
            }
            return results;
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }

}
