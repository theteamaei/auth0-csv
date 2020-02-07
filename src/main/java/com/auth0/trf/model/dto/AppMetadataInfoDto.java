package com.auth0.trf.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppMetadataInfoDto {

    @SerializedName("organisation_id")
    @Expose()
    private int organisationId;

    @SerializedName("roles")
    @Expose()
    private List<RoleDto> roleDtoList;

    public int getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(int organisationId) {
        this.organisationId = organisationId;
    }

    public List<RoleDto> getRoleDtoList() {
        return roleDtoList;
    }

    public void setRoleDtoList(List<RoleDto> roleDtoList) {
        this.roleDtoList = roleDtoList;
    }

}
