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
class SQLInjectionTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void whenSQLInjectionAttempt_thenReturns400Or401() throws Exception {
        String[] maliciousInputs = {
                "' OR '1'='1' --",
                "' OR '1'='1' #",
                "admin' --",
                "' OR 1=1; --",
                "' OR 'a'='a",
                "' UNION SELECT NULL, NULL, NULL --"
        };

        for (String input : maliciousInputs) {
            String json = mapper.writeValueAsString(LoginInputDTO.builder()
                    .username(input)
                    .password("password")
                    .build()
            );

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                    )
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        }
    }
}