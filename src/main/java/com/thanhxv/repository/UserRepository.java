package com.thanhxv.repository;

import com.thanhxv.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(@NotBlank String username);

    Optional<User> findByEmail(@NotBlank String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(@NotBlank String username);

    Boolean existsByEmail(@NotBlank String email);

}
