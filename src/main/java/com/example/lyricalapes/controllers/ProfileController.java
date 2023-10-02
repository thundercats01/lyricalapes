package com.example.lyricalapes.controllers;

import com.example.lyricalapes.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProfileController {

    UserRepo usersDAO;


    public ProfileController(UserRepo usersDAO) {
        this.usersDAO = usersDAO;
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "profile/profileview";
    }



}
