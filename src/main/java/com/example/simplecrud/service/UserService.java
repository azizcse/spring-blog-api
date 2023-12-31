package com.example.simplecrud.service;

import com.example.simplecrud.payload.UserIdentityAvailability;
import com.example.simplecrud.payload.UserSummary;
import com.example.simplecrud.security.UserPrincipal;

public interface UserService {
    UserSummary getCurrentUser(UserPrincipal currentUser);
    UserIdentityAvailability checkUser(String email);
}
