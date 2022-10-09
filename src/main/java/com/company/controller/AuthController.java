package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.dto.request.*;
import com.company.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;


    @ApiOperation(value = "Login", notes = "Method for Authorization")
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login (@RequestBody ProfileLoginDTO dto) {
        ProfileDTO profileDTO = authService.login(dto);
        log.info("LOGIN : ADMIN : {}", dto);
        return ResponseEntity.ok(profileDTO);
    }

    @PostMapping("/registration/student")
    public ResponseEntity<?> registrationStudent (@RequestBody @Valid ProfileStudentDTO dto) {
        ProfileDTO respone = authService.registrationStudent(dto);
        log.info("AUTH Controller: REGISTRATION STUDENT: {}", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(respone);
    }


    @PostMapping("/update/student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @RequestBody @Valid ProfileStudentUpdateDTO dto) {
        log.info("AUTH Controller: UPDATE STUDENT : {}", dto);
        return ResponseEntity.ok(authService.updateStudent(id, dto));
    }


    @PostMapping("/registration/teacher")
    public ResponseEntity<?> registrationT(@RequestBody @Valid ProfileTeacherDTO dto) {
        ProfileDTO response = authService.registrationTeacher(dto);
        log.info("AUTH Controller: REGISTRATION TEACHER : {}", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/update/teacher/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable String id, @RequestBody @Valid ProfileTeacherUpdateDTO dto) {
        log.info("AUTH Controller: UPDATE TEACHER : {}", dto);
        return ResponseEntity.ok(authService.updateTeacher(id, dto));
    }
}
