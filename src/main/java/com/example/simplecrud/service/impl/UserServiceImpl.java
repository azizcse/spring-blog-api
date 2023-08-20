package com.example.simplecrud.service.impl;

import com.example.simplecrud.payload.UserSummary;
import com.example.simplecrud.repository.UserRepository;
import com.example.simplecrud.security.UserPrincipal;
import com.example.simplecrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserSummary getCurrentUser(UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFirstName(),
                currentUser.getLastName());
    }
}
