package com.example.group07.project2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Controller
@SpringBootApplication
public class Project2Application {

    /**
     * Landing page (When someone is not logged in)
     */
    @RequestMapping("/")
    String home() {
        return "home";
    }

    @RequestMapping("/login")
    String login() {
        return "login";
    }

    @RequestMapping("/signUp")
    String signUp() {
        return "signUp";
    }

    @RequestMapping("/editProfile")
    String editProfile() {
        return "editProfile";
    }


    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
    }

}
