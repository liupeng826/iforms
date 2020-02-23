package com.microastudio.iforms.common.utils;

/**
 * 权限
 *
 * @author peng
 */
public enum EmailEnum {

    /**
     * PDF
     */
    PDF("Pdf", 1),

    /**
     * Survey
     */
    SURVEY("PoorFeedBackToCustomer", 2);

    private String name;
    private int value;

    EmailEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static EmailEnum getByValue(int value) {
        for (EmailEnum roleEnum : values()) {
            if (roleEnum.getValue() == value) {
                return roleEnum;
            }
        }
        return null;
    }
}
