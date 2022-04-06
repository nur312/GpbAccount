package gpb.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GpbAccountApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBalance() throws Exception {

        mockMvc.perform(get("/bank/balance"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0.0")));
    }

    @Test
    void deposit() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/bank/deposit")
                        .content(asJsonString(200.0))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void withdraw() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/bank/deposit")
                        .content(asJsonString(200.0))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/bank/withdraw")
                        .content(asJsonString(200.0))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void combine() throws Exception {
        var current = Double.parseDouble(mockMvc.perform(MockMvcRequestBuilders
                        .get("/bank/balance")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/bank/deposit")
                .content(asJsonString(200.0))
                .contentType(MediaType.APPLICATION_JSON)
        );

        mockMvc.perform(MockMvcRequestBuilders
                .post("/bank/withdraw")
                .content(asJsonString(100.0))
                .contentType(MediaType.APPLICATION_JSON)
        );

        var result = Double.parseDouble(mockMvc.perform(MockMvcRequestBuilders
                        .get("/bank/balance")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString());

        Assertions.assertEquals(current + 100.0, result);


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
