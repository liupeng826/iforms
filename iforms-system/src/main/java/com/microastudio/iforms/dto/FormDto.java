package com.microastudio.iforms.dto;

import com.microastudio.iforms.domain.Question;
import com.microastudio.iforms.domain.Section;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author peng
 */
@Data
public class FormDto implements Serializable {

    private static final long serialVersionUID = -6764362501696187002L;

    private String title;
    private String description;
    private String level;
    private String marketId;
    private String dealerId;
//    private String customerName;
//    private String customerEmail;
//    private String contactNo;
    private String systemToken;
    private byte sendEmail;
    private String type;
    private String isActive;
    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
    private String language;

    private byte includeSection;
    List<SectionDto> sections;
}
