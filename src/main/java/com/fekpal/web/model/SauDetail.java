package com.fekpal.web.model;

import com.fekpal.common.base.BaseModel;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by APone on 2018/2/19 17:43.
 */
@Component
public class SauDetail extends BaseModel{

    private static final long serialVersionUID = -2469190456785094149L;

    private String sauName;

    private String adminName;

    private String logo;

    private Timestamp foundTime;

    private String description;

    private String contactEmail;

    private String contactNumber;

    public String getSauName() {
        return sauName;
    }

    public void setSauName(String sauName) {
        this.sauName = sauName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Timestamp getFoundTime() {
        return foundTime;
    }

    public void setFoundTime(Timestamp foundTime) {
        this.foundTime = foundTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
