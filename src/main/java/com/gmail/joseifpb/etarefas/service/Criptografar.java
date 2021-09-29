/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb.etarefas.service;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author jose
 */
public class Criptografar {

    public static String hashPassword(String password) {
        String pass = "";

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
            pass = hash.toString(16);
        } catch (Exception e) {
            System.err.println("Erro encriot password" + e.getMessage());
        }
        return pass;
    }
}
