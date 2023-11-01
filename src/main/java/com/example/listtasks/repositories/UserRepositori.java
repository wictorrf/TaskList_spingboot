package com.example.listtasks.repositories;

import com.example.listtasks.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepositori extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    UserDetails findByName(String name);
}
