package com.liga.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liga.backend.domain.dto.UserEditDto;
import com.liga.backend.domain.dto.UserListDto;
import com.liga.backend.domain.dto.UserRegistrationDto;
import com.liga.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    UserService service;
    MockMvc mockMvc;
    ObjectMapper mapper;

    UserListDto listDto;
    UserEditDto editDto;
    UserRegistrationDto registrationDto;

    @BeforeEach
    void setUp() {
        service = mock(UserService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(service)).build();
        mapper = new ObjectMapper();

        listDto = UserListDto.builder()
                .fullName("fullName")
                .sex('f')
                .city("Raccon")
                .build();
        editDto = UserEditDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .sex('f')
                .city("Raccon")
                .build();
        registrationDto = UserRegistrationDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .build();
    }

    @Test
    void createUser() throws Exception {
        when(service.createUser(any())).thenReturn(editDto);
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(registrationDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(editDto)));
    }

    @Test
    void updateUser() throws Exception {
        when(service.updateUser(any())).thenReturn(editDto);
        mockMvc.perform(put("/api/users/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(editDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(editDto)));
    }

    @Test
    void getUser() throws Exception {
        when(service.getUser(any())).thenReturn(editDto);
        mockMvc.perform(get("/api/users/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(editDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(editDto)));
    }

    @Test
    void search() throws Exception {
        when(service.findUsersByParam(any(), any(), any())).thenReturn(Collections.singleton(listDto));
        mockMvc.perform(get("/api//search")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(Collections.singleton(listDto))))
                .andExpect(status().isOk());
    }
}