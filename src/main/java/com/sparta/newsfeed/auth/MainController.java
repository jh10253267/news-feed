package com.sparta.newsfeed.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/user/login-page")
    public String loginPage() {
        System.out.println("-loginPage-");
        return "login";
    }
}
