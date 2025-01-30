package com.intech.dev.intweet.routes;

import com.intech.dev.intweet.repository.BanRepository;
import com.intech.dev.intweet.repository.UserRepository;
import com.intech.dev.intweet.utils.JWTUtils;
import jakarta.persistence.EntityNotFoundException;
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
class UserTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BanRepository banRepository;
    @Autowired
    private UserRepository userRepository;

    private final String token = JWTUtils.generateToken(this.user, UUID.randomUUID().toString());
    private final String user = "UserTest";


    @Test
    @Sql(scripts = "/test-data.sql")
    void whenGetUserProfile_thenReturnUserInfos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/profile")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(this.user))
                .andExpect(MockMvcResultMatchers.jsonPath("$.admin").value(true))
                .andReturn();
    }

    @Test
    @Sql(scripts = "/test-data.sql")
    void whenBanUser_thenAddToBanList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/ban/" + this.user)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertTrue(
            banRepository.isUserBanned(
                    userRepository.findByUsername(this.user)
                            .orElseThrow(() -> new EntityNotFoundException("User not found"))
                            .getId()
            )
        );
    }
}
