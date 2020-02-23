package com.microastudio.iforms.modules.form.dto;

import java.io.Serializable;

/**
 * @author peng
 */
public class EmailDto implements Serializable {

    private static final long serialVersionUID = 3887956324790126849L;

    private String emailType;
    private String language;

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
