package com.microastudio.iforms.modules.system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
* @author peng
*/
@Data
public class DeptDto implements Serializable {

    private Long id;

    private String deptId;

    private String name;

    @NotNull
    private byte isActive;

    private Long pid;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptDto> children;

    private Timestamp createdDate;

    public String getLabel() {
        return name;
    }
}
