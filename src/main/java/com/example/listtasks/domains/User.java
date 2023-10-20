package com.example.listtasks.domains;

import com.example.listtasks.dtos.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 50)
    private String name;

    @Column(unique = true)
    @Email(message = "campo email está invalido")
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;


    public User(UserDto data) {
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.userType = data.userType();
    }
}
