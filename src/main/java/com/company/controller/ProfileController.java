package com.company.controller;

import com.company.config.details.EntityDetails;
import com.company.dto.ProfileDTO;
import com.company.dto.request.ProfileStudentDTO;
import com.company.dto.response.ApiResponseDTO;
import com.company.entity.ProfileEntity;
import com.company.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/profile")
@RestController
@Slf4j
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/adm/create/student")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody @Valid ProfileStudentDTO dto) {
        ProfileEntity profile = EntityDetails.getProfile();
        ProfileDTO response = profileService.createStudentByAdmin(dto);
        log.info("PROFILE Controller: ADMIN CREATE STUDENT : {}", dto);
        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/adm/change_status/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable("id") String id){

        ApiResponseDTO dto = profileService.changeStatus(id);
        log.info("PROFILE Controller: ADMIN CHANGE STATUS : {}", id);
        return ResponseEntity.ok(dto);
    }


    @PostMapping("/adm/profile_list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProfileDTO>> getList () {
        List<ProfileDTO> filter = profileService.getList();
        log.info("PROFILE Controller: ADMIN GET STUDENTS LIST : {}", getList());
        return ResponseEntity.ok().body(filter);
    }


    @PostMapping("/adm/profile_list/status/active")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProfileDTO>> getProfileListStatusActive () {
        List<ProfileDTO> filter = profileService.getListProfileStatusActive();
        log.info("PROFILE Controller: ADMIN GET LIST ACTIVE STUDENTS : {}", getList());
        return ResponseEntity.ok().body(filter);
    }



    @PostMapping("/adm/profile_list/status/block")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProfileDTO>> getProfileListStatusBlock () {
        List<ProfileDTO> filter = profileService.getListProfileStatusBlock();
        log.info("PROFILE Controller: ADMIN GET LIST BLOCK STUDENTS : {}", getList());
        return ResponseEntity.ok().body(filter);
    }


    @GetMapping("/public/full_info/{login}")
    public ResponseEntity<?> profileFullInfo(@PathVariable ("login") String login) {
        ProfileDTO response = profileService.getProfileFullInfo(login);
        log.info("PROFILE Controller: SHOW INFO LOGIN : {}", login);
        return ResponseEntity.ok().body(response);
    }
}
