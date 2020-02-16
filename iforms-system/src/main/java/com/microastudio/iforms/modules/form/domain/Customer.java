package com.microastudio.iforms.modules.form.domain;

import java.io.Serializable;

/**
 * @author peng
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = -7202005722989410480L;

    private Long id;
    private String customerName;
    private String customerEmail;
    private String contactNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
