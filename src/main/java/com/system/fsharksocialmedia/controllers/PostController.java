package com.system.fsharksocialmedia.controllers;

import com.system.fsharksocialmedia.dtos.PostDto;
import com.system.fsharksocialmedia.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Code mẫu
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts() {
        return ResponseEntity.ok(postService.getAll());

    }
}
