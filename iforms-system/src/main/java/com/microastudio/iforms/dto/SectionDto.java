package com.microastudio.iforms.dto;

import com.microastudio.iforms.domain.Question;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SectionDto implements Serializable {

    private static final long serialVersionUID = -1944382737578744212L;

    private Long id;
    private String description;
    private Integer sequence;
    List<Question> questions;
}
