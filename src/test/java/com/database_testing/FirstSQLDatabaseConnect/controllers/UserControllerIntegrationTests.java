package com.database_testing.FirstSQLDatabaseConnect.controllers;

import com.database_testing.FirstSQLDatabaseConnect.TestDataUtil;
import com.database_testing.FirstSQLDatabaseConnect.domain.DTO.UserDTO;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.UserEntity;
import com.database_testing.FirstSQLDatabaseConnect.mappers.Mapper;
import com.database_testing.FirstSQLDatabaseConnect.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private UserService userService;
    private Mapper<UserEntity, UserDTO> authorMapper;

    @Autowired
    public UserControllerIntegrationTests(MockMvc mockMvc, UserService userService, Mapper<UserEntity, UserDTO> authorMapper){
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.userService = userService;
        this.authorMapper = authorMapper;
    }

    @Test
    public void POSTtestCreates201() throws Exception {
        UserEntity author1 = TestDataUtil.createTestAuthor1();
        author1.setId(null);
        String authorjson = objectMapper.writeValueAsString(author1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorjson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testReturnsAuthor() throws Exception {
        UserEntity author1 = TestDataUtil.createTestAuthor1();
        author1.setId(null);
        String authorjson = objectMapper.writeValueAsString(author1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorjson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(author1.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(author1.getAge())
        );
    }
    @Test
    public void GETtestAllAuthorsGetHas200Exception() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        );
    }
    @Test
    public void GETtestReturnsAllAuthors() throws Exception {
        UserEntity author1 = TestDataUtil.createTestAuthor1();
        author1.setId(null);
        String authorjson = objectMapper.writeValueAsString(author1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorjson)
        );

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(author1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(author1.getAge()))
        ;
    }
    @Test
    public void GETtestOneAuthorGetHas200Exception() throws Exception {
        UserEntity userEntity = TestDataUtil.createTestAuthor1();
        userEntity.setId(null);
        userEntity = userService.saveAuthor(userEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/"+ userEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userEntity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(userEntity.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(userEntity.getName()))
        ;
    }
    @Test
    public void GETtestOneAuthorGetHas404Exception() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/authors/1")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }
    @Test
    public void PUTtestOneAuthorGetHas404Exception() throws Exception {
        UserDTO userDTO = TestDataUtil.createTestDTOauthor1();

        UserEntity userEntity = userService.saveAuthor(authorMapper.mapFrom(userDTO));
        userEntity.setAge(12);
        userEntity.setName("new name");

        String stringauthor = objectMapper.writeValueAsString(userEntity);


        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stringauthor)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(userEntity.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(userEntity.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userEntity.getId()))

        ;
    }
}
