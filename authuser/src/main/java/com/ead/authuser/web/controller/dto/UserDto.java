package com.ead.authuser.web.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String username;
    private String email;
    private String password;
    private String oldPassword;
    private String fullName;
    private String phone;
    private String document;
    private String imageUrl;
}
