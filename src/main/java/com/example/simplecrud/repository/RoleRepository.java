package com.example.simplecrud.repository;

import com.example.simplecrud.model.role.Role;
import com.example.simplecrud.model.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName name);
}
