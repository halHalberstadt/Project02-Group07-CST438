package com.example.group07.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
@Autowired
    UserRepository userRepository;


    @RequestMapping(value="/allUsers",method= RequestMethod.GET)
    public String userLsit(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "allUsers";
    }

    @RequestMapping(value="/addUser",method= RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("user", userRepository.findAll());
        return "signUp";
    }


}
