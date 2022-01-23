package com.labrelata.modulworkshop.controller;

import com.labrelata.modulworkshop.constant.ResponseCode;
import com.labrelata.modulworkshop.service.MahasiswaService;
import com.labrelata.modulworkshop.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@EnableWebMvc
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = MahasiswaController.class)
@ActiveProfiles("Test")
class MahasiswaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MahasiswaService mahasiswaService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void createMahasiswa_Success() throws Exception {
        when(mahasiswaService.create(any()))
                .thenReturn(ResponseUtil.build(ResponseCode.SUCCESS, "Success", null, HttpStatus.OK));
        mockMvc.perform(post("/mahasiswa")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void createMahasiswa_Exception() {
        when(mahasiswaService.create(any())).thenThrow(IllegalStateException.class);
        assertThrows(Exception.class, () -> mockMvc.perform(post("/mahasiswa")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andReturn());
    }

    @Test
    void getAllMahasiswa_Success() throws Exception {
        when(mahasiswaService.getAll())
                .thenReturn(ResponseUtil.build(ResponseCode.SUCCESS, "Success", null, HttpStatus.OK));
        mockMvc.perform(get("/mahasiswa")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getAllMahasiswa_Exception() {
        when(mahasiswaService.getAll()).thenThrow(IllegalStateException.class);
        assertThrows(Exception.class, () -> mockMvc.perform(get("/mahasiswa")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andReturn());
    }

    @Test
    void getByIdMahasiswa_Success() throws Exception {
        when(mahasiswaService.getById(anyLong()))
                .thenReturn(ResponseUtil.build(ResponseCode.SUCCESS, "Success", null, HttpStatus.OK));
        mockMvc.perform(get("/mahasiswa/1")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getByIdMahasiswa_Exception() {
        when(mahasiswaService.getById(anyLong())).thenThrow(IllegalStateException.class);
        assertThrows(Exception.class, () -> mockMvc.perform(get("/mahasiswa/1")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andReturn());
    }

    @Test
    void updateMahasiswa_Success() throws Exception {
        when(mahasiswaService.update(any(), anyLong()))
                .thenReturn(ResponseUtil.build(ResponseCode.SUCCESS, "Success", null, HttpStatus.OK));
        mockMvc.perform(put("/mahasiswa/1")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void updateMahasiswa_Exception() {
        when(mahasiswaService.update(any(), anyLong())).thenThrow(IllegalStateException.class);
        assertThrows(Exception.class, () -> mockMvc.perform(put("/mahasiswa/1")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andReturn());
    }

    @Test
    void deleteMahasiswa_Success() throws Exception {
        when(mahasiswaService.delete(anyLong()))
                .thenReturn(ResponseUtil.build(ResponseCode.SUCCESS, "Success", null, HttpStatus.OK));
        mockMvc.perform(delete("/mahasiswa/1")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteMahasiswa_Exception() {
        when(mahasiswaService.delete(anyLong())).thenThrow(IllegalStateException.class);
        assertThrows(Exception.class, () -> mockMvc.perform(delete("/mahasiswa/1")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andReturn());
    }

}