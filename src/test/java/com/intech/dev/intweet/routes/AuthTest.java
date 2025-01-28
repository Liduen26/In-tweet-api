package com.intech.dev.intweet.routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intech.dev.intweet.dto.input.SignUpInputDTO;
import com.intech.dev.intweet.utils.JWTUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthTest {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper mapper = new ObjectMapper();

    private final String token = JWTUtils.generateToken(this.user, UUID.randomUUID().toString());
    private final String user = "UserTest";

    @Test
    void whenSigningUp_thenReturns200() throws Exception {
        String json = mapper.writeValueAsString( SignUpInputDTO.builder()
                .username("TestSign")
                .password("password")
                .build()
        );

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Sql(scripts = "/test-data.sql")
    void whenLoggingIn_thenReturns200() throws Exception {
        String json = mapper.writeValueAsString( SignUpInputDTO.builder()
                .username(user)
                .password("password")
                .build()
        );

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }


    @Test
    void whenNoToken_thenReturns401() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/profile"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Sql(scripts = "/test-data.sql")
    void whenAuthenticated_thenReturns200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/profile")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Sql(scripts = "/test-data.sql")
    void whenNonAdminTryAdminRoute_thenReturns403() throws Exception {
        // Execute the request with non admin user
        String token = JWTUtils.generateToken("Liduen", UUID.randomUUID().toString());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/ban/" + this.user)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                )
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

}
