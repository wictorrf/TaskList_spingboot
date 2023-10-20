package com.example.listtasks.controller;

import com.example.listtasks.domains.User;
import com.example.listtasks.dtos.UserDto;
import com.example.listtasks.services.UserService;
import com.example.listtasks.services.validations.ResponseApi;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseApi<User>> createUser(@RequestBody @Valid UserDto data){
        try {
            return userService.createUser(data);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(new ResponseApi<>(false, errorMessage, null));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> listUsers = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(listUsers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<User>> updateUser(@RequestBody @Valid UserDto data, @PathVariable(value = "id") Long id){
        try {
            return userService.updateUser(data, id);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(new ResponseApi<>(false, errorMessage, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<User>> deleteUser(@PathVariable(value = "id") Long id){
        try {
            return this.userService.deleteUser(id);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(new ResponseApi<>(false, errorMessage, null));
        }
    }
}
