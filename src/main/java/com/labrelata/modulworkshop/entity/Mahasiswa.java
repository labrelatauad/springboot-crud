package com.labrelata.modulworkshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "mahasiswa")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Mahasiswa extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 6457306702294344545L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nim", nullable = false)
    private String nim;

    @Column(name = "prodi", nullable = false)
    private String prodi;

    @Column(name = "email")
    private String email;

}
