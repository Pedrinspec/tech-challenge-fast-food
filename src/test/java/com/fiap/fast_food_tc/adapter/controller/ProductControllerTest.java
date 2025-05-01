package com.fiap.fast_food_tc.adapter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.fast_food_tc.adapter.dto.CategoryResponseDto;
import com.fiap.fast_food_tc.adapter.dto.ProductRequestDto;
import com.fiap.fast_food_tc.adapter.dto.ProductResponseDto;
import com.fiap.fast_food_tc.app.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;


    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnOkForSave() throws Exception {
        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .name("p1")
                .description("P1 description")
                .imageUrl("image.url.jpeg")
                .isAvailable("").productValue("10").quantity("")
                .build();

        Mockito.when(productService.create(Mockito.any(ProductRequestDto.class))).thenReturn(productResponseDto);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productResponseDto)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.name").value("p1"))
                        .andExpect(jsonPath("$.description").value("P1 description"));
    }


    @Test
    public void shouldUpdateTest() throws Exception{
        // given - precondition or setup
        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .name("p1")
                .description("P1 description")
                .imageUrl("image.url.jpeg")
                .isAvailable("").productValue("10").quantity("")
                .productId(1l)
                .build();


        ProductResponseDto updatedResponseDTO = ProductResponseDto.builder()
                .name("p1")
                .description("P2 description")
                .imageUrl("image2.url.jpeg")
                .isAvailable("").productValue("10").quantity("")
                .build();

        // when -  action or the behaviour that we are going test

        Mockito.when(productService.update(Mockito.anyLong(),Mockito.any(ProductRequestDto.class))).thenReturn(updatedResponseDTO);

        mockMvc.perform(put("/product/{id}", productResponseDto.getProductId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedResponseDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("P2 description"));
    }


    // Test method for deleting a Person
    @Test
    void shouldDeleteProduct() throws Exception {

        long idToDelete = 1L;
        doNothing().when(productService).delete(idToDelete);

        mockMvc.perform(delete("/product/{id}", idToDelete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).delete(idToDelete);
    }


    @Test
    void shouldGetByCategory() throws Exception {
        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .name("p1")
                .description("P2 description")
                .imageUrl("image2.url.jpeg")
                .isAvailable("").productValue("10").quantity("").category(new CategoryResponseDto(1l, "category"))
                .build();
        List<ProductResponseDto> list = new ArrayList<>();
        list.add(productResponseDto);
        Mockito.when(productService.getByCategoryId(Mockito.anyLong())).thenReturn(list);
        Assertions.assertThat(list.size()).isGreaterThan(0);


    }
}