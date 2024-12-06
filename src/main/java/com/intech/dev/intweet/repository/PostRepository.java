package com.intech.dev.intweet.repository;

import com.intech.dev.intweet.entity.Post;
import com.intech.dev.intweet.repository.projection.PostWithLikeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("SELECT new com.intech.dev.intweet.repository.projection.PostWithLikeCount( p.id, p.title, p.body, p.status, p.createdAt, p.user, COUNT(l) )" +
            "FROM Post p " +
                "LEFT JOIN Like l ON l.post.id = p.id " +
            "GROUP BY p.id")
    Optional<List<PostWithLikeCount>> findByParentIsNullWithLikeCount();
}
