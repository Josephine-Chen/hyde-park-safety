package com.hydeparksafety.service.impl;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

/**
 * Created by HSong on 12/11/2017.
 */
@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

//    @Autowired
//    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
//        User user = new User();
//        user.setUsername(connection.getDisplayName());
//        user.setPassword(randomAlphabetic(8));
//        userRepository.save(user);
//        return user.getUsername();

        return "";
    }
}