package com.example.lyricalapes.controllers;

import com.example.lyricalapes.models.User;
import com.example.lyricalapes.repositories.CommentRepo;
import com.example.lyricalapes.repositories.UserRepo;
import com.example.lyricalapes.repositories.VerseRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class ProfileController {

    private UserRepo usersDAO;
    private VerseRepo verseRepo;
    private CommentRepo commentRepo;

    public ProfileController(UserRepo usersDAO, VerseRepo verseRepo, CommentRepo commentRepo) {
        this.usersDAO = usersDAO;
        this.verseRepo = verseRepo;
        this.commentRepo = commentRepo;
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        // Get the logged-in user
        User loggedInPrinciple = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = usersDAO.findByUsername(loggedInPrinciple.getUsername());

        // Fetch posts and comments made by the logged-in user
        model.addAttribute("verses", verseRepo.findAllByUser(loggedInUser));
        model.addAttribute("comments", commentRepo.findAll());

        return "profile/profileview";
    }


    @GetMapping("/profile/{id}")
    public String ShowOtherUserProfile(@PathVariable Long id, Model model) {
        User clickedUser = usersDAO.findById(id).get();
        model.addAttribute("user", clickedUser);
        return "profile/usersprofileview";
    }

    @PostMapping("/post_delete")
    public String RemoveSelectedPost(@PathVariable @RequestParam Long postid) {

        verseRepo.deleteById(postid);

        return "profile/usersprofileview";
    }

}
