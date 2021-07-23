package com.markusdel.apirestfullspringboot.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profile")
public class ProfileController {

    @Value("${spring.profiles.active}")
    private String profileActive;

    @GetMapping
    public ProfileActive getProfileActive() {
        var active = new ProfileActive();
        active.profileActive = profileActive;
        return active;
    }

}

class ProfileActive {

    public String profileActive;
}