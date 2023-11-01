package com.example.listtasks.services.validations;

import com.example.listtasks.domains.UserRole;
import com.example.listtasks.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ValidationUser {

    public <T> ResponseEntity<ResponseApi<T>> validationUserDto(UserDto data) {
        if (data.name().length() > 10) {
            return ResponseApi.error("O campo name deve ter no maximo 10 caracteres!");
        }
        if (data.email().isEmpty() || data.email() == null) {
            return ResponseApi.error("A campo email deve ser preenchido!");
        }
        if (data.password().isEmpty() || data.password() == null) {
            return ResponseApi.error("O campo password deve ser preenchido1");
        }
        if (data.userRole() != UserRole.ADMIN && data.userRole() != UserRole.COMMON) {
            return ResponseApi.error("O campo userType deve ser preenchido com tipo ADMIN ou COMMON!");
        }

        return ResponseApi.success("Dados validados com sucesso!", null);
    }
}
