package com.auth0.trf.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMetadataInfoDto {

    @SerializedName("title")
    @Expose()
    private String title;

    @SerializedName("phone_number")
    @Expose()
    private String phoneNumber;

    @SerializedName("fax")
    @Expose()
    private String fax;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

}
