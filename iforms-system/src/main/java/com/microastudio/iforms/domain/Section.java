package com.microastudio.iforms.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author peng
 */
@Data
public class Section implements Serializable {

    private static final long serialVersionUID = 1499493098289132285L;

    private Long id;
    private String title;
    private String description;
    private Integer sequence;
}