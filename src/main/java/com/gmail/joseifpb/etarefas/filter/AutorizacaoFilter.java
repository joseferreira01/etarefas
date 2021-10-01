/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.filter;
import com.gmail.joseifpb.etarefas.entity.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jose2
 */
@WebFilter("*.xhtml")
public class AutorizacaoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        System.err.println("entro o filtro ID  ");
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        Long user = null;
        if (request.getRequestURI().endsWith("/faces/index.xhtml")
                || request.getRequestURI().endsWith("/faces/user/edit.xhtml")
               
                
                || request.getRequestURI()
                .contains("/javax.faces.img/")) {
            // System.err.println("no if filter");
            chain.doFilter(req, res);
            // System.err.println("index filtro");
        } else {
            try {
                user = (Long) session.getAttribute("users");
            } catch (Exception e) {
                System.err.println("exep filte "+e.getMessage());
                 response.sendRedirect(request.getContextPath()
                        + "/faces/index.xhtml");
            }
            // System.err.println("passou do filtro com usuario " + user.getNome()+" id "+user.getId());
            if (user != null) {
                  
                chain.doFilter(req, res);
            } else {
                // throw new RuntimeException("faça login para ter acesso!");
                response.sendRedirect(request.getContextPath()
                        + "/faces/index.xhtml");
                System.err.println("else do uuu ");
            }
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}