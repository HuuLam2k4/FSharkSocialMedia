package com.system.fsharksocialmedia.controllers;

import com.system.fsharksocialmedia.dtos.UserDto;
import com.system.fsharksocialmedia.services.AdminProfileByUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/profile")
public class AdminProfileByUserController {
    @Autowired
    private AdminProfileByUserService adminProfileByUserService;

    @GetMapping("/{username}")
    public UserDto getByUsername(@PathVariable String username) {
        return adminProfileByUserService.getByUsername(username);
    }
}
