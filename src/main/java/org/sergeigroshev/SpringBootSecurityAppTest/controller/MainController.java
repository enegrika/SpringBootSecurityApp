package org.sergeigroshev.SpringBootSecurityAppTest.controller;

import org.sergeigroshev.SpringBootSecurityAppTest.entity.User;
import org.sergeigroshev.SpringBootSecurityAppTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Sergei Y Groshev
 * @version 1.0
 */


@RestController
public class MainController {


    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    //simple home page
    @GetMapping("/")
    public String homePage() {
        return "Home Page ";
    }

    // Principal - info about Username
    @GetMapping("/authenticated")
    public String pageForAuthorisedUsers(Principal principal) {
//        return "this is secured authorised area ! "
//                + " your username is : " + principal.getName();

        User user = userService.findByUsername(principal.getName());

        return "this is secured authorised area ! "
                + " your username is : " + user.getUsername()
                + " your email is : " + user.getEmail();
    }


    @GetMapping("/read_profile")
    public String pageForReadProfile() {
        return "read users profile by users";
    }

    @GetMapping("/admin_page")
    public String pageOnlyADMIN() {
        return "ADMIN's space";
    }
}
