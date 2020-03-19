package com.microastudio.iforms.modules.form.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author peng
 */
public class Form implements Serializable {

    private static final long serialVersionUID = 7126526811516176046L;

    private String superFormId;
    private Long id;
    private String title;
    private String description;
    private String level;
    private String marketId;
    private String deptId;
    private String client;
    private byte sendEmail;
    private String type;
    private byte isActive;
    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
    private Timestamp deadline;
    private String language;
    private byte includeSection;

    public String getSuperFormId() {
        return superFormId;
    }

    public void setSuperFormId(String superFormId) {
        this.superFormId = superFormId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public byte getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(byte sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public byte getIncludeSection() {
        return includeSection;
    }

    public void setIncludeSection(byte includeSection) {
        this.includeSection = includeSection;
    }
}
