package com.sbz.cdss.controller;

import com.sbz.cdss.controller.dto.AuthenticationRequest;
import com.sbz.cdss.controller.dto.AuthenticationResponse;
import com.sbz.cdss.service.authentication.Login;
import com.sbz.cdss.service.authentication.data.LoginData;
import com.sbz.cdss.service.authentication.data.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private Login login;

    @Autowired
    public AuthenticationController(Login login) {
        this.login = login;
    }

    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        LoginResult loginResult = login.execute(new LoginData(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        return ResponseEntity.ok(new AuthenticationResponse(loginResult.getId(), loginResult.getToken()));    }

}
