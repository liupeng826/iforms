package com.microastudio.iforms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author peng
 */
public class Form implements Serializable {

    private static final long serialVersionUID = 7126526811516176046L;
    private Long id;
    private String title;
    private String description;
    private String level;
    private String marketId;
    private String dealerId;
    private String customerName;
    private String customerEmail;
    private String contactNo;
    private String systemToken;
    private byte sendEmail;
    private String type;
    private String isActive;
    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
    private String language;
}
