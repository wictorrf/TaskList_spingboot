package com.example.listtasks.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.listtasks.domains.Task;
import com.example.listtasks.domains.User;
import com.example.listtasks.dtos.TaskDto;
import com.example.listtasks.repositories.TaskRepository;
import com.example.listtasks.services.validations.ResponseApi;
import com.example.listtasks.services.validations.ValidationTask;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationTask validationTask;

    public ResponseEntity<ResponseApi<Task>> createTask(TaskDto data) {

        Optional<User> existUser = this.userService.findById(data.id());
        if (!existUser.isPresent()) {
            return ResponseApi.error("Usuário não encontrado!");
        }
        ResponseEntity<ResponseApi<Task>> validateResponses = validationTask.validationTaskDto(data);
        if (!validateResponses.getBody().isSuccess()) {
            return validateResponses;
        }

        User user = existUser.get();
        LocalDateTime time = LocalDateTime.now();
        Task newTask = new Task();
        newTask.setDescription(data.description());
        newTask.setUser(user);
        newTask.setTimestamp(time);
        this.taskRepository.save(newTask);
        return ResponseApi.success("Tarefa criada com sucesso", newTask);
    }

    public List<Task> getAllTask() {
        List<Task> listTasks = this.taskRepository.findAll();
        return listTasks;
    }
}
