package com.sbz.cdss.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class AuthenticationResponse {

    private long id;
    private String token;

}
