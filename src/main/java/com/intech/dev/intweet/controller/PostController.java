package com.intech.dev.intweet.controller;

import com.intech.dev.intweet.dto.output.PostOutputDTO;
import com.intech.dev.intweet.service.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostOutputDTO> getAllParentPosts() {
        List<PostOutputDTO> list = postService.getAllParentPosts();
        return list;
    }
}
