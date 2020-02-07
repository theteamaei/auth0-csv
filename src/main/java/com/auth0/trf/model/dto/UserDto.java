package com.auth0.trf.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class UserDto {

    @SerializedName("sub")
    @Expose(serialize = false)
    private String sub;

    @SerializedName("user_id")
    @Expose(serialize = false)
    private String userId;

    @SerializedName("family_name")
    @Expose()
    private String familyName;

    @SerializedName("given_name")
    @Expose()
    private String givenName;

    @SerializedName("name")
    @Expose()
    private String name;

    @SerializedName("password")
    @Expose()
    private String password;

    @SerializedName("picture")
    @Expose(serialize = false)
    private String picture;

    @SerializedName("email")
    @Expose()
    private String email;

    @SerializedName("last_login")
    @Expose(serialize = false)
    private Timestamp lastLogin;

    @SerializedName("user_metadata")
    @Expose()
    private UserMetadataInfoDto userMetadataInfoDto;

    @SerializedName("app_metadata")
    @Expose()
    private AppMetadataInfoDto appMetadataInfoDto;

    @SerializedName("connection")
    @Expose()
    private String connection;

    @SerializedName("client_id")
    @Expose()
    private String clientId;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public UserMetadataInfoDto getUserMetadataInfoDto() {
        return userMetadataInfoDto;
    }

    public void setUserMetadataInfoDto(UserMetadataInfoDto userMetadataInfoDto) {
        this.userMetadataInfoDto = userMetadataInfoDto;
    }

    public AppMetadataInfoDto getAppMetadataInfoDto() {
        return appMetadataInfoDto;
    }

    public void setAppMetadataInfoDto(AppMetadataInfoDto appMetadataInfoDto) {
        this.appMetadataInfoDto = appMetadataInfoDto;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
