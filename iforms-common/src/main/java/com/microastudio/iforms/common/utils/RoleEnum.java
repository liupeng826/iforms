package com.microastudio.iforms.common.utils;

/**
 * 权限
 *
 * @author peng
 */
public enum RoleEnum {

    /**
     * 超级管理员
     * 对应 @PreAuthorize("hasRole('SuperAdmin')")
     */
    SUPER_ADMIN("SuperAdmin", 10),

    /**
     * 普通管理员
     */
    ADMIN("Admin", 20),

    /**
     * 用户
     */
    USER("User", 30);

    private String name;
    private int value;

    RoleEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static RoleEnum getByValue(int value) {
        for (RoleEnum roleEnum : values()) {
            if (roleEnum.getValue() == value) {
                return roleEnum;
            }
        }
        return null;
    }
}
