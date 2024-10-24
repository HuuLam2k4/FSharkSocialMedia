package com.system.fsharksocialmedia.controllers;

import com.system.fsharksocialmedia.dtos.PostDto;
import com.system.fsharksocialmedia.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Code mẫu
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/content")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostDto>> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "7") int size,
            @RequestParam(required = false) String search) {

        Page<PostDto> posts = postService.getPost(page, size, search);
        return ResponseEntity.ok(posts);
    }

}
