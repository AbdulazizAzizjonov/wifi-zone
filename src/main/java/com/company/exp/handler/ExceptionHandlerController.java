package com.company.exp.handler;

import com.company.exp.AlreadyExistLogin;
import com.company.exp.BadRequestException;
import com.company.exp.NotPermissionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({AlreadyExistLogin.class, BadRequestException.class, NotPermissionException.class})
    public ResponseEntity<?> badRequestException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
