package com.sbz.cdss.service.authentication;

import com.sbz.cdss.model.User;
import com.sbz.cdss.repository.UserRepository;
import com.sbz.cdss.security.JWTUtils;
import com.sbz.cdss.service.authentication.data.LoginData;
import com.sbz.cdss.service.authentication.data.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;

@Service
public class Login {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public Login(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResult execute(LoginData data) {
        User user = userRepository.findByUsername(data.getUsername()).orElseThrow(NotFoundException::new);

        if (!passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            throw new NotFoundException("");
        }

        return new LoginResult(user.getId(), JWTUtils.generateToken(user));
    }

}
