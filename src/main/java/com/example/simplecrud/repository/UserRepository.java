package com.example.simplecrud.repository;

import com.example.simplecrud.exception.ResourceNotFoundException;
import com.example.simplecrud.model.user.User;
import com.example.simplecrud.security.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(@NotBlank String username);

    Optional<User> findByEmail(@NotBlank String email);

    Boolean existsByUsername(@NotBlank String username);

    Boolean existsByEmail(@NotBlank String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    default User getUser(UserPrincipal currentUser) {
        return getUserByName(currentUser.getUsername());
    }

    default User getUserByName(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }


}
