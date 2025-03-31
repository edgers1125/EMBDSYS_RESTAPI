package com.database_testing.FirstSQLDatabaseConnect.controllers;

import com.database_testing.FirstSQLDatabaseConnect.TestDataUtil;
import com.database_testing.FirstSQLDatabaseConnect.domain.DTO.TransactionDTO;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.TransactionEntity;
import com.database_testing.FirstSQLDatabaseConnect.mappers.Mapper;
import com.database_testing.FirstSQLDatabaseConnect.services.TransactionService;
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
public class TransactionControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private TransactionService transactionService;
    private Mapper<TransactionEntity, TransactionDTO> bookMapper;

    @Autowired
    public TransactionControllerIntegrationTests(MockMvc mockMvc, TransactionService transactionService, Mapper<TransactionEntity, TransactionDTO> bookMapper){
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.transactionService = transactionService;
        this.bookMapper = bookMapper;
    }

    @Test
    public void PUTtestCreateOrUpdateBook() throws Exception {
        TransactionDTO testBook = TestDataUtil.CreateTestBook(null);
        String bookjson = objectMapper.writeValueAsString(testBook);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+testBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookjson)
                        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(testBook.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(testBook.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(testBook.getAuthor()));
    }

    @Test
    public void GETtestBookOKException() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/books")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void GETtestBookreturnsallbooks() throws Exception {
        TransactionEntity transactionEntity1 = TestDataUtil.CreateTestBook2(null);
        transactionService.saveBook("random", transactionEntity1);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/books")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value(transactionEntity1.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(transactionEntity1.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value(transactionEntity1.getUserEntity()))
        ;
    }
    @Test
    public void GETtestBookreturnsaBook() throws Exception {
        TransactionEntity transactionEntity1 = TestDataUtil.CreateTestBook2(null);
        transactionService.saveBook("random", transactionEntity1);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/books/"+ transactionEntity1.getIsbn())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(transactionEntity1.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(transactionEntity1.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(transactionEntity1.getUserEntity()))
        ;
    }
    @Test
    public void GETtestBookSingularReturns404() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/books/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }
    @Test
    public void GETtestUpdateBook() throws Exception {
        TransactionEntity transactionEntity1 = TestDataUtil.CreateTestBook2(null);
        TransactionDTO transactionDTO = bookMapper.mapTo(transactionEntity1);
        transactionService.saveBook(transactionEntity1.getIsbn(), transactionEntity1);

        transactionDTO.setTitle("nigger");
        transactionDTO.setIsbn("whopper");

        String bookjson = objectMapper.writeValueAsString(transactionDTO);


        mockMvc.perform(
                        MockMvcRequestBuilders.put("/books/"+ transactionEntity1.getIsbn())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookjson)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(transactionDTO.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(transactionDTO.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(transactionDTO.getAuthor()))

        ;
    }
    @Test
    public void PATCHtestBookSingularReturns404() throws Exception {
        TransactionEntity book = TestDataUtil.CreateTestBook2(null);
        transactionService.saveBook(book.getIsbn(),book);

        book.setTitle("Noowaps");

        String bookjson = objectMapper.writeValueAsString(book);

        mockMvc.perform(
                        MockMvcRequestBuilders.patch("/books/"+book.getIsbn())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookjson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(book.getUserEntity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle()))
        ;
    }
    @Test
    public void DELETEsingularBook() throws Exception{
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/books/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }
}
