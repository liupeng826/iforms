package com.microastudio.iforms.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author peng
 */
@Data
public class Form implements Serializable {

    private static final long serialVersionUID = 7126526811516176046L;

    private Long id;
    private String title;
    private String description;
    private String level;
    private String marketId;
    private String dealerId;
    private String systemToken;
    private byte sendEmail;
    private String type;
    private byte isActive;
    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
    private String language;
    private byte includeSection;
}
