package com.example.listtasks.repositories;

import com.example.listtasks.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UserRepositori extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    List<User> findByName(String name);
}
