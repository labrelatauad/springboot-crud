package com.labrelata.modulworkshop.service;

import com.labrelata.modulworkshop.constant.ResponseCode;
import com.labrelata.modulworkshop.entity.ApiResponse;
import com.labrelata.modulworkshop.entity.Mahasiswa;
import com.labrelata.modulworkshop.form.MahasiswaForm;
import com.labrelata.modulworkshop.repository.MahasiswaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MahasiswaService.class)
@ActiveProfiles("test")
class MahasiswaServiceTest {

    @MockBean
    private MahasiswaRepository mahasiswaRepository;

    @Autowired
    private MahasiswaService mahasiswaService;

    @Test
    void createMahasiswa_Success() {
        MahasiswaForm form = MahasiswaForm.builder()
                .name("Maverick")
                .nim("123")
                .email("test@email.local")
                .prodi("Teknik Informatika")
                .build();
        ResponseEntity<Object> responseEntity = mahasiswaService.create(form);
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        verify(mahasiswaRepository, times(1)).save(any());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals(ResponseCode.SUCCESS, Objects.requireNonNull(apiResponse).getCode());
    }

    @Test
    void createMahasiswa_Exception() {
        when(mahasiswaRepository.save(any())).thenThrow(IllegalStateException.class);
        MahasiswaForm form = MahasiswaForm.builder()
                .name("Maverick")
                .nim("123")
                .email("test@email.local")
                .prodi("Teknik Informatika")
                .build();
        assertThrows(Exception.class, () -> mahasiswaService.create(form));
    }

    @Test
    void getAllMahasiswa_Success() {
        when(mahasiswaRepository.findAll()).thenReturn(List.of(
                Mahasiswa.builder().id(1L).name("Maverick").nim("121").prodi("Teknik Informatika").build(),
                Mahasiswa.builder().id(2L).name("Johnson").nim("122").prodi("Teknik Informatika").build(),
                Mahasiswa.builder().id(3L).name("John Doe").nim("123").prodi("Teknik Informatika").build()
        ));
        ResponseEntity<Object> responseEntity = mahasiswaService.getAll();
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        List<Mahasiswa> mahasiswaList = (List<Mahasiswa>) apiResponse.getData();

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals(ResponseCode.SUCCESS, Objects.requireNonNull(apiResponse).getCode());
        assertEquals(3, mahasiswaList.size());
    }

    @Test
    void getAllMahasiswa_Exception() {
        when(mahasiswaRepository.findAll()).thenThrow(IllegalStateException.class);
        assertThrows(Exception.class, () -> mahasiswaService.getAll());
    }

    @Test
    void getByIdMahasiswa_Success() {
        when(mahasiswaRepository.findById(anyLong()))
                .thenReturn(Optional.of(Mahasiswa.builder().id(1L).name("Maverick").nim("121").prodi("Teknik Informatika").build()));

        ResponseEntity<Object> responseEntity = mahasiswaService.getById(anyLong());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        Mahasiswa mahasiswa = (Mahasiswa) Objects.requireNonNull(apiResponse).getData();

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals(ResponseCode.SUCCESS, Objects.requireNonNull(apiResponse).getCode());
        assertEquals(1L, mahasiswa.getId());
    }

    @Test
    void getByIdMahasiswa_NotFound() {
        when(mahasiswaRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Object> responseEntity = mahasiswaService.getById(anyLong());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCodeValue());
        assertEquals(ResponseCode.NOT_FOUND, Objects.requireNonNull(apiResponse).getCode());
    }

    @Test
    void getByIdMahasiswa_Exception() {
        when(mahasiswaRepository.findById(anyLong())).thenThrow(IllegalStateException.class);
        assertThrows(Exception.class, () -> mahasiswaService.getById(anyLong()));
    }

    @Test
    void updateMahasiswa_Success() {
        MahasiswaForm form = MahasiswaForm.builder()
                .name("Maverick")
                .nim("123")
                .email("test@email.local")
                .prodi("Teknik Informatika")
                .build();
        when(mahasiswaRepository.findById(anyLong()))
                .thenReturn(Optional.of(Mahasiswa.builder().id(1L).name("Maverick").nim("121").prodi("Teknik Informatika").build()));

        ResponseEntity<Object> responseEntity = mahasiswaService.update(form, anyLong());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        Mahasiswa mahasiswa = (Mahasiswa) Objects.requireNonNull(apiResponse).getData();

        verify(mahasiswaRepository, times(1)).save(any());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals(ResponseCode.SUCCESS, Objects.requireNonNull(apiResponse).getCode());
        assertEquals(1L, mahasiswa.getId());
    }

    @Test
    void updateMahasiswa_NotFound() {
        when(mahasiswaRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Object> responseEntity = mahasiswaService.update(any(), 1L);
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCodeValue());
        assertEquals(ResponseCode.NOT_FOUND, Objects.requireNonNull(apiResponse).getCode());
    }

    @Test
    void updateMahasiswa_Exception() {
        when(mahasiswaRepository.findById(anyLong())).thenThrow(IllegalStateException.class);
        assertThrows(Exception.class, () -> mahasiswaService.update(any(), anyLong()));
    }

    @Test
    void deleteMahasiswa_Success() {
        when(mahasiswaRepository.findById(anyLong()))
                .thenReturn(Optional.of(Mahasiswa.builder().id(1L).name("Maverick").nim("121").prodi("Teknik Informatika").build()));

        ResponseEntity<Object> responseEntity = mahasiswaService.delete(anyLong());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();

        verify(mahasiswaRepository, times(1)).delete(any());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals(ResponseCode.SUCCESS, Objects.requireNonNull(apiResponse).getCode());
    }

    @Test
    void deleteMahasiswa_NotFound() {
        when(mahasiswaRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Object> responseEntity = mahasiswaService.delete(anyLong());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCodeValue());
        assertEquals(ResponseCode.NOT_FOUND, Objects.requireNonNull(apiResponse).getCode());
    }

    @Test
    void deleteMahasiswa_Exception() {
        when(mahasiswaRepository.findById(anyLong())).thenThrow(IllegalStateException.class);
        assertThrows(Exception.class, () -> mahasiswaService.delete(anyLong()));
    }

}