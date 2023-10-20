package com.example.listtasks.services.validations;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.listtasks.dtos.TaskDto;

@Service
public class ValidationTask {
    
    public <T> ResponseEntity<ResponseApi<T>> validationTaskDto(TaskDto data){

        if(data.description().isEmpty() || data.description() == null){
            return ResponseApi.error("O campo description deve ser preenchido!");
        }
        if(data.id() == null){
            return ResponseApi.error("Voce deve adicionar essa tarefa a um usuario!");
        }

        return ResponseApi.success("Dados validados com sucesso!", null);
    }
}
