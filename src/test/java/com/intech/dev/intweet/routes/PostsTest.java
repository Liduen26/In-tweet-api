package com.intech.dev.intweet.routes;


import com.intech.dev.intweet.repository.PostRepository;
import com.intech.dev.intweet.utils.JWTUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PostsTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PostRepository postRepository;

    private final String token = JWTUtils.generateToken(this.user, UUID.randomUUID().toString());
    private final String user = "UserTest";


    @Test
    @Sql(scripts = "/test-data.sql")
    void whenGetAllParentPosts_thenReturnsListOfPosts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/post")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Test2"))
                .andReturn();
    }

    @Test
    @Sql(scripts = "/test-data.sql")
    void whenDeletePost_thenReturns200AndRemovedFromDb() throws Exception {
        Long postId = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/post/" + postId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertFalse(postRepository.existsById(postId));
    }
}


