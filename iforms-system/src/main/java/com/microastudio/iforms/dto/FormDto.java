package com.microastudio.iforms.dto;

import com.microastudio.iforms.domain.Question;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author peng
 */
@Data
public class FormDto {

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
    List<Question> questions;
}
