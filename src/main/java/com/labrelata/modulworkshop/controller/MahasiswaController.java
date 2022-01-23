package com.labrelata.modulworkshop.controller;

import com.labrelata.modulworkshop.form.MahasiswaForm;
import com.labrelata.modulworkshop.service.MahasiswaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/mahasiswa")
public class MahasiswaController {

    @Autowired
    private MahasiswaService mahasiswaService;

    @PostMapping(value = "")
    public ResponseEntity<Object> create(@RequestBody MahasiswaForm form) {
        try {
            return mahasiswaService.create(form);
        } catch (Exception e) {
            log.error("Terjadi error create mahasiswa: ", e);
            throw e;
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAll() {
        try {
            return mahasiswaService.getAll();
        } catch (Exception e) {
            log.error("Terjadi error get all mahasiswa: ", e);
            throw e;
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            return mahasiswaService.getById(id);
        } catch (Exception e) {
            log.error("Terjadi error get by id mahasiswa: ", e);
            throw e;
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,
                                         @RequestBody MahasiswaForm form) {
        try {
            return mahasiswaService.update(form, id);
        } catch (Exception e) {
            log.error("Terjadi error update mahasiswa: ", e);
            throw e;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            return mahasiswaService.delete(id);
        } catch (Exception e) {
            log.error("Terjadi error delete mahasiswa: ", e);
            throw e;
        }
    }

}
