package com.labrelata.modulworkshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse <T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 7808225252024071676L;

    private String code;

    private String message;

    private T data;

}
