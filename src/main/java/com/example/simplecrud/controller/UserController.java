package com.example.simplecrud.controller;

import com.example.simplecrud.payload.UserIdentityAvailability;
import com.example.simplecrud.payload.UserSummary;
import com.example.simplecrud.security.CurrentUser;
import com.example.simplecrud.security.UserPrincipal;
import com.example.simplecrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserSummary> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = userService.getCurrentUser(currentUser);
        return new ResponseEntity< >(userSummary, HttpStatus.OK);
    }

    @GetMapping("/exist")
    public ResponseEntity<UserIdentityAvailability> checkUsernameAvailability(@RequestParam(value = "email") String username) {
        System.out.println("User check with :"+username);
        UserIdentityAvailability userIdentityAvailability = userService.checkUser(username);

        return new ResponseEntity< >(userIdentityAvailability, HttpStatus.OK);
    }
}
