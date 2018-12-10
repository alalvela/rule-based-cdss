package com.sbz.cdss.service.authentication.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class LoginResult {

    private long id;
    private String token;

}
