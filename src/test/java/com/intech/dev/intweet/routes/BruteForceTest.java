package com.intech.dev.intweet.routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intech.dev.intweet.dto.input.LoginInputDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BruteForceTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void whenBruteForceAttempt_thenReturns401() throws Exception {
        String[] passwords = {"hearthstonematoutpris", "thispasswordisguzzzzz", "123456", "iwantoplayrl", "letmeinnnnnnnnnnn", "aaaaaaaaaaaaaa"};

        for (String password : passwords) {
            String json = mapper.writeValueAsString(LoginInputDTO.builder()
                    .username("test")
                    .password(password)
                    .build()
            );

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                    )
                    .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        }
    }
}
