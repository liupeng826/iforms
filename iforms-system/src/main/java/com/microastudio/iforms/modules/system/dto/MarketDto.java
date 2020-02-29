package com.microastudio.iforms.modules.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author peng
 */
@Data
public class MarketDto implements Serializable {

    private String id;

    private String name;

    private byte isActive;
}
