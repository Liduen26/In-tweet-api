package com.intech.dev.intweet.service;

import com.intech.dev.intweet.dto.output.PostOutputDTO;
import com.intech.dev.intweet.entity.Post;
import com.intech.dev.intweet.mapper.PostMapper;
import com.intech.dev.intweet.repository.PostRepository;
import com.intech.dev.intweet.repository.projection.PostWithLikeCount;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public List<PostOutputDTO> getAllParentPosts() {
        return postRepository.findByParentIsNullWithLikeCount()
                .orElseThrow(() -> new EntityNotFoundException("No posts found"))
                .stream()
                .map(postMapper::entityWithLikesToOutputDTO)
                .toList();
    }

    public void deletePostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post with ID " + id + " not found when trying to delete it"));
        postRepository.delete(post);
    }
}
