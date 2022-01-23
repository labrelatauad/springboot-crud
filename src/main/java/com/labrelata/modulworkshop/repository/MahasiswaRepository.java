package com.labrelata.modulworkshop.repository;

import com.labrelata.modulworkshop.entity.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {
}
