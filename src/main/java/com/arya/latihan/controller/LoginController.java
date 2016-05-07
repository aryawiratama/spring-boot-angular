/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arya.latihan.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Artha
 */
@RestController
public class LoginController {
    
    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public Principal getUser(Principal user){
        System.out.println("Masuk sini");
        return user;
    }
}
