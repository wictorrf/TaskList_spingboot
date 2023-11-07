package com.example.listtasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.listtasks.domains.Task;
import com.example.listtasks.domains.User;
import com.example.listtasks.domains.UserRole;
import com.example.listtasks.repositories.TaskRepository;
import com.example.listtasks.services.TaskService;
import com.example.listtasks.services.UserService;
import com.example.listtasks.services.validations.ValidationTask;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;

    @Mock
    private ValidationTask validationTask;

    @Autowired
    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should verify if the return of listTasks is ok")
    void createTaskCase1() throws Exception {
        User newUser = new User(1L, "Marcos", "marcos@email.com", "marcos12345",
                UserRole.ADMIN);
        Optional<User> optionalUser = Optional.of(newUser);
        when(userService.findById(1L)).thenReturn(optionalUser);

        Task request = new Task(1L, "Teste descricao", newUser, LocalDateTime.now());

        when(taskRepository.findAll()).thenReturn(List.of(request));

        assertEquals(List.of(request), taskService.getAllTask());

        verify(taskRepository).findAll();
        verifyNoMoreInteractions(taskRepository);

    }

}
