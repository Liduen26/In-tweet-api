package com.intech.dev.intweet.controller;

import com.intech.dev.intweet.dto.output.PostOutputDTO;
import com.intech.dev.intweet.service.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<PostOutputDTO> getAllParentPosts() {
        List<PostOutputDTO> list = postService.getAllParentPosts();
        return list;
    }

    // TODO : Add preAuthorize ADMIN
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
    }
}
