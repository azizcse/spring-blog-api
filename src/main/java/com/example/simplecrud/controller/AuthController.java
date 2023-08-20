package com.example.simplecrud.controller;

import com.example.simplecrud.exception.AppException;
import com.example.simplecrud.exception.BlogapiException;
import com.example.simplecrud.model.role.Role;
import com.example.simplecrud.model.role.RoleName;
import com.example.simplecrud.model.user.User;
import com.example.simplecrud.payload.ApiResponse;
import com.example.simplecrud.payload.JwtAuthenticationResponse;
import com.example.simplecrud.payload.LoginRequest;
import com.example.simplecrud.payload.SignUpRequest;
import com.example.simplecrud.repository.RoleRepository;
import com.example.simplecrud.repository.UserRepository;
import com.example.simplecrud.security.JwtTokenProvider;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.crypto.SecretKey;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
class AuthController {
	private static final String USER_ROLE_NOT_SET = "User role not set";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private void secret(){
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		byte[] keyBytes = key.getEncoded();

		String base64Key = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(keyBytes);
		System.out.println("Generated key: " + base64Key);
	}
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println("Login method called");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtTokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		/*if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
			throw new BlogapiException(HttpStatus.BAD_REQUEST, "Username is already taken");
		}*/
		if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
			throw new BlogapiException(HttpStatus.BAD_REQUEST, "Email is already taken");
		}

		System.out.println("Signup api called************"+userRepository.count());
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
		User result = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{userId}")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE, "User registered successfully"));
	}

}
