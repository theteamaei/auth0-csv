package com.auth0.trf.util;

import com.auth0.trf.model.dto.AppMetadataInfoDto;
import com.auth0.trf.model.dto.RoleDto;
import com.auth0.trf.model.dto.UserDto;
import com.auth0.trf.model.dto.UserMetadataInfoDto;
import com.opencsv.CSVReader;
import org.apache.commons.collections4.ListUtils;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AuthUtil {
    private static final int maxSize = 2000;
    private static final String AUTH0_CONNECTION = "IELTSDatabase";
    private static final String GENERAL_PASSWORD = "CH4NG3P4SSW0RD!";

    public List<UserDto> getAccountList(String filename, String connection) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fis,
                    StandardCharsets.UTF_8);
            CSVReader reader = new CSVReader(isr);

            List<String[]> rows = reader.readAll();
            List<UserDto> userDtoList = new ArrayList<>();
            rows.remove(0);
            for (String[] row: rows) {
                UserDto userDtoTest = userDtoList.stream()
                        .filter(user -> user.getUserId().equals(row[0]))
                        .findFirst().orElse(null);
                if (userDtoTest != null) {
                    RoleDto roleDto = new RoleDto();
                    roleDto.setRoleName(row[10]);
                    userDtoTest.getAppMetadataInfoDto().getRoleDtoList().add(roleDto);
                } else {
                    UserDto userDto = new UserDto();
                    userDto.setConnection(connection);
                    userDto.setPassword(GENERAL_PASSWORD);
                    userDto.setUserId(row[0]);
                    userDto.setGivenName(row[2]);
                    userDto.setFamilyName(row[3]);
                    userDto.setName(row[2] + " " + row[3]);
                    userDto.setEmail(row[4]);

                    List<RoleDto> roleDtoList = new ArrayList<>();
                    RoleDto roleDto = new RoleDto();
                    roleDto.setRoleName(row[10]);
                    roleDtoList.add(roleDto);

                    AppMetadataInfoDto appMetadataInfoDto = new AppMetadataInfoDto();
                    appMetadataInfoDto.setRoleDtoList(roleDtoList);
                    appMetadataInfoDto.setOrganisationId(Integer.parseInt(row[8]));

                    UserMetadataInfoDto userMetadataInfoDto = new UserMetadataInfoDto();
                    userMetadataInfoDto.setTitle(row[1]);
                    userMetadataInfoDto.setPhoneNumber(row[5]);
                    userMetadataInfoDto.setFax(row[6]);

                    userDto.setAppMetadataInfoDto(appMetadataInfoDto);
                    userDto.setUserMetadataInfoDto(userMetadataInfoDto);

                    userDtoList.add(userDto);
                }
            }

            System.out.println(userDtoList.size());
            return userDtoList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<List<UserDto>> partitionAccountList(List<UserDto> userDtoList) {
        List<List<UserDto>> partitionList = ListUtils.partition(userDtoList,  this.maxSize);
        return partitionList;
    }
}
