package com.thanhxv.repository;

import com.thanhxv.model.role.Role;
import com.thanhxv.model.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
