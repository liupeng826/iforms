package com.microastudio.iforms.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author peng
 */
@Data
public class SystemToken implements Serializable {

    private static final long serialVersionUID = 5368665276515547587L;

    private Long id;
    private String description;
    private String token;
}