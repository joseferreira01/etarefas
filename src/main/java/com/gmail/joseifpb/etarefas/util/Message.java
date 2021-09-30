/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.util;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author jose
 */
@RequestScoped
public class Message {
     public void addMessage (String massagr) {
        FacesMessage mensagem = new FacesMessage (massagr);
        FacesContext.getCurrentInstance ().addMessage (null, mensagem);
         FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().setKeepMessages(true);
    }
}
