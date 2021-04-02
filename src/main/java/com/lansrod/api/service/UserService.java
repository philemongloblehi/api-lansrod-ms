package com.lansrod.api.service;

import com.lansrod.api.entity.User;
import com.lansrod.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String signin(String username, String password) {
        return null;
    }

    public String signup(User user) {
        return null;
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

}
