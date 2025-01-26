package com.intech.dev.intweet.service;

import com.intech.dev.intweet.dto.output.PostOutputDTO;
import com.intech.dev.intweet.entity.Like;
import com.intech.dev.intweet.entity.Post;
import com.intech.dev.intweet.entity.User;
import com.intech.dev.intweet.mapper.PostMapper;
import com.intech.dev.intweet.repository.PostRepository;
import com.intech.dev.intweet.repository.UserRepository;
import com.intech.dev.intweet.repository.projection.PostWithLikeCount;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    public List<PostOutputDTO> getAllParentPosts() {
        return postRepository.findByParentIsNullWithLikeCount()
                .orElseThrow(() -> new EntityNotFoundException("No posts found"))
                .stream()
                .map(postMapper::entityWithLikesToOutputDTO)
                .toList();
    }

    public void createPost(String username, String body) throws Exception {
        User user = userRepository.findByUsername(username).get();

        if (user.getIsBanned()) {
            throw new Exception("L'utilisateur " + username + " est banni");
        }

        Post post = new Post();
        post.setParent(null);
        post.setUser(user);
        post.setBody(body);
        post.setStatus("TEST");
        post.setTitle("TITRE");
        post.setCreatedAt(LocalDateTime.now());

        postRepository.save(post);
    }

    public void deletePostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post with ID " + id + " not found when trying to delete it"));
        postRepository.delete(post);
    }
}
