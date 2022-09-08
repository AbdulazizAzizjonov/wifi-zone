package com.company.controller;

import com.company.config.details.EntityDetails;
import com.company.dto.ProfileDTO;
import com.company.dto.request.ProfileStudentDTO;
import com.company.dto.response.ApiResponseDTO;
import com.company.entity.ProfileEntity;
import com.company.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/profile")
@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/adm/create/student")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody @Valid ProfileStudentDTO dto) {

        ProfileEntity profile = EntityDetails.getProfile();
        ProfileDTO response = profileService.createStudentByAdmin(dto);
        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/adm/change_status/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable("id") String id){

        ApiResponseDTO dto = profileService.changeStatus(id);

        return ResponseEntity.ok(dto);
    }


    @PostMapping("/adm/profile_list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProfileDTO>> getList () {

        List<ProfileDTO> filter = profileService.getList();
        return ResponseEntity.ok().body(filter);
    }


    @PostMapping("/adm/profile_list/status/active")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProfileDTO>> getProfileListStatusActive () {

        List<ProfileDTO> filter = profileService.getListProfileStatusActive();
        return ResponseEntity.ok().body(filter);
    }



    @PostMapping("/adm/profile_list/status/block")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProfileDTO>> getProfileListStatusBlock () {

        List<ProfileDTO> filter = profileService.getListProfileStatusBlock();
        return ResponseEntity.ok().body(filter);
    }


    @GetMapping("/public/full_info/{login}")
    public ResponseEntity<?> profileFullInfo(@PathVariable ("login") String login) {

        ProfileDTO response = profileService.getProfileFullInfo(login);

        return ResponseEntity.ok().body(response);
    }
}
