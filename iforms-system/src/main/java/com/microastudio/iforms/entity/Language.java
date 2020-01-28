package com.microastudio.iforms.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author peng
 */
@Data
public class Language implements Serializable {

    private static final long serialVersionUID = 4429655148149492453L;

    private Long id;
    private String code;
    private String description;
    private byte isActive;
}