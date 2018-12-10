package com.sbz.cdss.service.user.data;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetUserData {

    private long id;
    private String username;
    private String password;

    public GetUserData(long id) {
        this.id = id;
    }

    public GetUserData(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
