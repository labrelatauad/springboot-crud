package com.labrelata.modulworkshop.service;

import com.labrelata.modulworkshop.constant.ResponseCode;
import com.labrelata.modulworkshop.entity.Mahasiswa;
import com.labrelata.modulworkshop.form.MahasiswaForm;
import com.labrelata.modulworkshop.repository.MahasiswaRepository;
import com.labrelata.modulworkshop.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MahasiswaService {

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    public ResponseEntity<Object> create(MahasiswaForm form) {
        log.info("Begin execute create new mahasiswa");
        log.info("Request:: [{}]", form);

        Mahasiswa mahasiswa = Mahasiswa.builder()
                .name(form.getName())
                .nim(form.getNim())
                .prodi(form.getProdi())
                .email(form.getEmail())
                .build();

        try {
            mahasiswaRepository.save(mahasiswa);
        } catch (Exception e) {
            log.error("Terjadi kesalahan saat create mahasiswa, error: ", e);
            throw e;
        }
        log.info("End execute create new mahasiswa");
        return ResponseUtil.build(ResponseCode.SUCCESS, "Success", mahasiswa, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAll() {
        log.info("Begin execute get all mahasiswa");
        List<Mahasiswa> mahasiswas;
        try {
            mahasiswas = mahasiswaRepository.findAll();
        } catch (Exception e) {
            log.error("Terjadi kesalahan saat get all mahasiswa, error: ", e);
            throw e;
        }
        log.info("End execute get all mahasiswa");
        return ResponseUtil.build(ResponseCode.SUCCESS, "Success", (Serializable) mahasiswas, HttpStatus.OK);
    }

    public ResponseEntity<Object> getById(Long id) {
        log.info("Begin execute get mahasiswaOptional by id: {}", id);
        Optional<Mahasiswa> mahasiswaOptional;
        try {
            mahasiswaOptional = mahasiswaRepository.findById(id);
        } catch (Exception e) {
            log.error("Terjadi kesalahan saat get mahasiswaOptional by id, error: ", e);
            throw e;
        }
        log.info("End execute get mahasiswaOptional by id");
        if (mahasiswaOptional.isEmpty())
            return ResponseUtil.build(ResponseCode.NOT_FOUND, "Data Not Found", null, HttpStatus.NOT_FOUND);

        return ResponseUtil.build(ResponseCode.SUCCESS, "Success", mahasiswaOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> update(MahasiswaForm form, Long id) {
        log.info("Begin execute update mahasiswa, id: {}", id);
        log.info("Request: [{}]", form);
        Mahasiswa mahasiswa;
        try {
            log.info("Find mahasiswa by id");
            Optional<Mahasiswa> mahasiswaOptional = mahasiswaRepository.findById(id);
            if (mahasiswaOptional.isEmpty()) {
                log.info("Mahasiswa not found");
                return ResponseUtil.build(ResponseCode.NOT_FOUND, "Data Not Found", null, HttpStatus.NOT_FOUND);
            }
            mahasiswa = mahasiswaOptional.get();
            mahasiswa.setName(form.getName());
            mahasiswa.setNim(form.getNim());
            mahasiswa.setProdi(form.getProdi());
            mahasiswa.setEmail(form.getEmail());

            log.info("Save updated mahasiswa");
            mahasiswaRepository.save(mahasiswa);
        } catch (Exception e) {
            log.error("Terjadi kesalahan saat update mahasiswa, error: ", e);
            throw e;
        }
        log.info("End execute update mahasiswa");
        return ResponseUtil.build(ResponseCode.SUCCESS, "Success", mahasiswa, HttpStatus.OK);
    }

    public ResponseEntity<Object> delete(Long id) {
        log.info("Begin execute delete mahasiswa, id: {}", id);
        try {
            log.info("Find mahasiswa by id");
            Optional<Mahasiswa> mahasiswaOptional = mahasiswaRepository.findById(id);
            if (mahasiswaOptional.isEmpty()) {
                log.info("Mahasiswa not found");
                return ResponseUtil.build(ResponseCode.NOT_FOUND, "Data Not Found", null, HttpStatus.NOT_FOUND);
            }
            mahasiswaRepository.delete(mahasiswaOptional.get());
        } catch (Exception e) {
            log.error("Terjadi kesalahan saat delete mahasiswa, error: ", e);
            throw e;
        }
        log.info("Begin execute delete mahasiswa");
        return ResponseUtil.build(ResponseCode.SUCCESS, "Success", null, HttpStatus.OK);
    }

}
