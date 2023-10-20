package com.example.listtasks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.listtasks.domains.Task;
import com.example.listtasks.dtos.TaskDto;
import com.example.listtasks.services.TaskSercvice;
import com.example.listtasks.services.validations.ResponseApi;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private TaskSercvice taskSercvice;

    @PostMapping("/register")
    public ResponseEntity<ResponseApi<Task>> createTask(@RequestBody @Valid TaskDto data){
        try {
            return this.taskSercvice.createTask(data);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(new ResponseApi<>(false, errorMessage, null));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> listTasks = this.taskSercvice.getAllTask();
        return ResponseEntity.status(HttpStatus.OK).body(listTasks);
    }
}
