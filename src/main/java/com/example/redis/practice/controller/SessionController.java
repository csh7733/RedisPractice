package com.example.redis.practice.controller;

import com.example.redis.practice.dto.RequestLoginDto;
import com.example.redis.practice.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class SessionController {

    @PostMapping
    public String login(@RequestBody RequestLoginDto requestLoginDto, HttpSession session) {
        session.setAttribute("user", new SessionUser(requestLoginDto));
        return "Session data set";
    }

    @GetMapping
    public String getSession(HttpSession session) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        if (user == null) {
            return "No session data found";
        }
        return "Session data: " + user.toString();
    }
}
