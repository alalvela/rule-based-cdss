package com.sbz.cdss.service.user;

import com.sbz.cdss.model.User;
import com.sbz.cdss.repository.UserRepository;
import com.sbz.cdss.service.user.data.GetUserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;

@Service
public class GetUser {

    private UserRepository userRepository;

    @Autowired
    public GetUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User byId(GetUserData data) {
        return userRepository.findById(data.getId()).orElseThrow(NotFoundException::new);
    }

    public User byUsername(GetUserData data) {
        return userRepository.findByUsername(data.getUsername()).orElseThrow(NotFoundException::new);
    }
}
