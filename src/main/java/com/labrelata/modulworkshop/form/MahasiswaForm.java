package com.labrelata.modulworkshop.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MahasiswaForm implements Serializable {

    private static final long serialVersionUID = 8257829147775459008L;

    private String name;

    private String nim;

    private String prodi;

    private String email;

}
