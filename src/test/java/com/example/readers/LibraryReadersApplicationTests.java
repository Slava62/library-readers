package com.example.readers;

import com.example.readers.model.Reader;
import com.example.readers.service.LibraryReaderService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryReadersApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    LibraryReaderService libraryReaderService;

    private Reader reader;

    @BeforeEach
    public void setUp(){
        reader = new Reader();
        reader.setId(1L);
        reader.setName("Nasha Masha");
        reader.setCardid(12045L);
    }

    @Test
    public  void  createReaderTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/readers")
                .content(objectMapper.writerFor(Reader.class)
                        .writeValueAsString(reader))
                .contentType("application/json"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        int actual = result.andReturn().getResponse().getStatus();
        int expected = 200;
        Assertions.assertEquals(expected, actual);
        Logger.getLogger(
                this.getClass().getName()).log(Level.INFO, "Response of reader creating: " +
                result.andReturn().getResponse().getContentAsString());
    }

    @Test
    public  void  getReaderByIdTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/readers")
                .content(objectMapper.writerFor(Reader.class)
                        .writeValueAsString(reader))
                .contentType("application/json"));
        //result.andExpect(MockMvcResultMatchers.status().isOk());

        String expected = result.andReturn().getResponse().getContentAsString();
        String actual = mockMvc.perform(MockMvcRequestBuilders
                .get("/readers" + "/" + reader.getId())
                .contentType("application/json")).andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(expected, actual);
        Logger.getLogger(
                this.getClass().getName()).log(Level.INFO, "Response of getting existing reader: " +
                result.andReturn().getResponse().getContentAsString());
    }

}
