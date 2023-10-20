package com.example.listtasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.listtasks.domains.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
