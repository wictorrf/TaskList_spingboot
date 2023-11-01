package com.example.listtasks.services;

import com.example.listtasks.domains.User;
import com.example.listtasks.dtos.UserDto;
import com.example.listtasks.repositories.UserRepositori;
import com.example.listtasks.services.validations.ResponseApi;
import com.example.listtasks.services.validations.ValidationUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepositori userRepositori;

    @Autowired
    private ValidationUser validationUser;

    @Transactional
    public ResponseEntity<ResponseApi<User>> createUser(UserDto data) {
        ResponseEntity<ResponseApi<User>> validateResponse = validationUser.validationUserDto(data);
        if (!validateResponse.getBody().isSuccess()) {
            return validateResponse;
        }
        UserDetails nameUserRegistered = findByName(data.name());
        if (nameUserRegistered != null) {
            return ResponseApi.error("Esse name de usuario ja existe no banco de dados!");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword, data.userRole());
        this.userRepositori.save(newUser);
        return ResponseApi.success("Usuário cadastrado com sucesso!", newUser);
    }

    public UserDetails findByName(String name) {
        UserDetails user = this.userRepositori.findByName(name);
        return user;
    }

    public Optional<User> findById(Long id) {
        Optional<User> user = this.userRepositori.findById(id);
        return user;
    }

    public List<User> getAllUsers() {
        List<User> listUsers = this.userRepositori.findAll();
        return listUsers;
    }

    public ResponseEntity<ResponseApi<User>> updateUser(UserDto data, Long id) {

        Optional<User> existUserOptional = userRepositori.findById(id);
        ResponseEntity<ResponseApi<User>> validateResponse = validationUser.validationUserDto(data);
        if (!validateResponse.getBody().isSuccess()) {
            return validateResponse;
        }
        if (existUserOptional.isPresent()) {
            User existUser = existUserOptional.get();
            existUser.setName(data.name());
            existUser.setEmail(data.email());
            existUser.setPassword(data.password());
            if (existUser.getUserRole() != data.userRole()) {
                return ResponseApi.error("Não pode alterar o tipo de usuario");
            }
            this.userRepositori.save(existUser);
            return ResponseApi.success("Usuario atualizado com sucesso", existUser);
        } else {
            return ResponseApi.error("Usuario nao encontrado");
        }
    }

    public ResponseEntity<ResponseApi<User>> deleteUser(Long id) {
        Optional<User> existUserOptional = userRepositori.findById(id);
        if (existUserOptional.isPresent()) {
            this.userRepositori.deleteById(id);
            return ResponseApi.success("Usuario deletado com sucesso", null);
        } else {
            return ResponseApi.error("Usuario nao encontrado");
        }
    }
}
