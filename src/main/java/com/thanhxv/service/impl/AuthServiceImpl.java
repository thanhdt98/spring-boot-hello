package com.thanhxv.service.impl;

import com.thanhxv.exception.AppException;
import com.thanhxv.exception.BlogapiException;
import com.thanhxv.model.role.Role;
import com.thanhxv.model.role.RoleName;
import com.thanhxv.model.user.User;
import com.thanhxv.payload.request.SignUpRequest;
import com.thanhxv.repository.RoleRepository;
import com.thanhxv.repository.UserRepository;
import com.thanhxv.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private static final String USER_ROLE_NOT_SET = "User role not set";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void checkCredentials(SignUpRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            throw new BlogapiException(HttpStatus.BAD_REQUEST, "Username is already taken");
        }
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            throw new BlogapiException(HttpStatus.BAD_REQUEST, "Email is already taken");
        }
    }

    @Override
    public User registerUser(SignUpRequest signUpRequest) {
        String firstName = signUpRequest.getFirstName().toLowerCase();
        String lastName = signUpRequest.getLastName().toLowerCase();
        String username = signUpRequest.getUsername().toLowerCase();
        String email = signUpRequest.getEmail().toLowerCase();
        String password = passwordEncoder.encode(signUpRequest.getPassword());
        User user = new User(firstName, lastName, username, email, password);

        List<Role> roles = new ArrayList<>();
        if (userRepository.count() == 0) {
            roles.add(roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
            roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        } else {
            roles.add(roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        }
        user.setRoles(roles);

        return userRepository.save(user);
    }
}
