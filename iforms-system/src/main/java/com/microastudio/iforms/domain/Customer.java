package com.microastudio.iforms.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author peng
 */
@Data
public class Customer implements Serializable {

    private static final long serialVersionUID = -7202005722989410480L;

    private Long id;
    private String customerName;
    private String customerEmail;
    private String contactNo;
}
