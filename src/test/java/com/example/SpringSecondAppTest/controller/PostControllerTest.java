//package com.example.SpringSecondAppTest.controller;
//
//import com.example.SpringSecondAppTest.test.controller.ProductController;
//import com.example.SpringSecondAppTest.test.entity.Product;
//import com.example.SpringSecondAppTest.test.service.ProductService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(ProductController.class)
//@AutoConfigureJsonTesters
//class PostControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private JacksonTester<Product> jsonProduct;
//
//    @MockBean
//    private ProductService productService;
//
//    @Test
//    void findAllShouldReturnUser() throws Exception {
//        Product product = new Product(1L, "product1", null);
//        when(productService.findById(1L)).thenReturn(product);
//        mockMvc.perform(get("/products/{id}/reviews", 1L))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(jsonProduct.write(product).getJson()));
//
//    }
//}
