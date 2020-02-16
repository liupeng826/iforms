package com.microastudio.iforms.modules.form.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author peng
 */
public class Form implements Serializable {

    private static final long serialVersionUID = 7126526811516176046L;

    private String supperId;
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

    public String getSupperId() {
        return supperId;
    }

    public void setSupperId(String supperId) {
        this.supperId = supperId;
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

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getSystemToken() {
        return systemToken;
    }

    public void setSystemToken(String systemToken) {
        this.systemToken = systemToken;
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

    public byte getIncludeSection() {
        return includeSection;
    }

    public void setIncludeSection(byte includeSection) {
        this.includeSection = includeSection;
    }
}
