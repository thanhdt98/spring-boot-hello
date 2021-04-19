package com.thanhxv.service.impl;

import com.thanhxv.exception.AppException;
import com.thanhxv.exception.BadRequestException;
import com.thanhxv.exception.ResourceNotFoundException;
import com.thanhxv.model.role.Role;
import com.thanhxv.model.role.RoleName;
import com.thanhxv.model.user.User;
import com.thanhxv.payload.ApiResponse;
import com.thanhxv.repository.RoleRepository;
import com.thanhxv.repository.UserRepository;
import com.thanhxv.service.UserService;
import com.thanhxv.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User addUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Username is already taken");
            throw new BadRequestException(apiResponse);
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email is already taken");
            throw new BadRequestException(apiResponse);
        }

        List<Role> roles = new ArrayList<>();
        roles.add(
                roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppConstants.POST, AppConstants.ID, id));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
