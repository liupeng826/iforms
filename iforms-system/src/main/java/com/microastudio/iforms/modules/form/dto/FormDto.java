package com.microastudio.iforms.modules.form.dto;

import com.microastudio.iforms.modules.form.domain.Answer;
import com.microastudio.iforms.modules.system.domain.Client;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author peng
 */
public class FormDto implements Serializable {

    private static final long serialVersionUID = -6764362501696187002L;

    private String superFormId;
    private Long id;
    private String title;
    private String description;
    private String level;
    private String marketId;
    private String deptId;
    private Client client;
    private byte sendEmail;
    private String type;
    private byte isActive;
    private byte publishStatus;
    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
    private String language;
    private String languageDescription;
    private Timestamp deadline;

    private byte includeSection;
    private List<SectionDto> sections;
    private List<Answer> answers;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
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

    public String getLanguageDescription() {
        return languageDescription;
    }

    public void setLanguageDescription(String languageDescription) {
        this.languageDescription = languageDescription;
    }

    public byte getIncludeSection() {
        return includeSection;
    }

    public void setIncludeSection(byte includeSection) {
        this.includeSection = includeSection;
    }

    public List<SectionDto> getSections() {
        return sections;
    }

    public void setSections(List<SectionDto> sections) {
        this.sections = sections;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public byte getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(byte publishStatus) {
        this.publishStatus = publishStatus;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
