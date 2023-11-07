package com.example.listtasks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.example.listtasks.domains.User;
import com.example.listtasks.domains.UserRole;
import com.example.listtasks.repositories.UserRepositori;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepositori userRepositori;

    @Test
    @DisplayName("Should return user successfully from db by id")
    void findUserById() {
        Long id = 1L;
        User data = new User(1L, "Wictor", "wictor@enail.com", "wictor12345", UserRole.COMMON);
        this.createUser(data);
        Optional<User> foundedUser = this.userRepositori.findById(id);
        assertThat(foundedUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not return user from db when user not exists")
    void findUserByIdCase2() {
        Long id = 1L;
        Optional<User> foundedUser = this.userRepositori.findById(id);
        assertThat(foundedUser.isEmpty()).isTrue();
    }

    private User createUser(User data) {
        User newUser = new User();
        this.entityManager.persist(newUser);
        return newUser;
    }
}
