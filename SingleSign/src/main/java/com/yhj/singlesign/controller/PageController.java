package com.yhj.singlesign.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/login")
    public String showLogin(String redirect, HttpServletRequest request) {
    	request.setAttribute("redirect", redirect);
    	System.out.println(redirect);
        return "login";
    }
    
    @RequestMapping("/")
    public String login() {
        return "index1";
    }
    
    @RequestMapping("/singleIndex1")
    public String singleIndex1() {
        return "index1";
    }
    @RequestMapping("/singleIndex2")
    public String singleIndex2() {
        return "index2";
    }
    
    @RequestMapping("/common")
    public String common() {
        return "common";
    }
}