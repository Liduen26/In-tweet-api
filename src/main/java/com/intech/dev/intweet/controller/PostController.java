package com.intech.dev.intweet.controller;

import com.intech.dev.intweet.annotation.IsAdmin;
import com.intech.dev.intweet.dto.input.PostInputDTO;
import com.intech.dev.intweet.dto.output.PostOutputDTO;
import com.intech.dev.intweet.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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

    @PostMapping
    public ResponseEntity<?> createPost(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid PostInputDTO input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessages.append(error.getDefaultMessage()).append("\n");
            });
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }

        try {
            postService.createPost(userDetails.getUsername(), input.getBody());
            return ResponseEntity.ok("OK");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("L'utilisateur " + userDetails.getUsername() + " n'existe pas");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @IsAdmin
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
    }
}
