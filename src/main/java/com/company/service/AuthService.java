package com.company.service;

import com.company.dto.ProfileDTO;
import com.company.dto.request.*;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfilePosition;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import com.company.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthService extends GeneralService<ProfileRepository>{

    public AuthService(ProfileRepository repository) {
        super(repository);
    }

    public ProfileDTO login (ProfileLoginDTO dto) {
        Optional<ProfileEntity> optional = repository.findByLogin(dto.getLogin());
        if (optional.isEmpty()) {
            log.error("AUTHSERVICE Login:  BUNDAY FOYDALANUVCHI MAVJUD EMAS(login) : {}", dto.getLogin());
            throw new BadRequestException("Bunday foydalanuvchi mavjud emas!");
        }

        ProfileEntity profile = optional.get();
        if (!profile.getPassword().equals(dto.getPassword())) {
            log.error("AUTHSERVICE Login:  PAROL NOTOG'RI : {}", dto.getPassword());
            throw new BadRequestException("Parol notog'ri!");
        }

        if (!profile.getStatus().equals(ProfileStatus.ACTIVE)) {
            log.error("AUTHSERVICE Login: FOYDALANUVCHI XOLATI BLOKLANGAN : {}", profile.getStatus());
            throw new BadRequestException("Ruxsat yo'q");
        }

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setSurname(profile.getSurname());
        profileDTO.setName(profile.getName());
        profileDTO.setJwt(JwtUtil.encode(profile.getLogin()));

        return profileDTO;
    }


    public ProfileDTO registrationStudent(ProfileStudentDTO dto) {
        Optional<ProfileEntity> entity = repository.findByLogin(dto.getLogin());
        if (entity.isPresent()) {
            log.error("AUTHSERVICE REGISTRATION STUDENT:  BUNDAY FOYDALANUVCHI MAVJUD (login) : {}", dto.getLogin());
            throw new BadRequestException("Bunday foydalanuvchi ro'yxatdan o'tgan!");
        }
        ProfileEntity profile = modelMapper.map(dto, ProfileEntity.class);
        profile.setRole(ProfileRole.ROLE_USER);
        profile.setStatus(ProfileStatus.BLOCK);
        profile.setDegree(ProfilePosition.TALABA);
        ProfileEntity save = repository.save(profile);

        return modelMapper.map(save,ProfileDTO.class);

    }


    public ProfileDTO updateStudent(String id, ProfileStudentUpdateDTO dto) {
        ProfileEntity profileEntity = repository.findById(id).orElseThrow(() -> {
            log.error("AUTHSERVICE UPDATE STUDENT:  BUNDAY FOYDALANUVCHI MAVJUD EMAS(id) : {}", id);
            throw new ItemNotFoundException("Bunday foydalanuvchi mavjud emas!");
        });
        modelMapper.map(dto,profileEntity);
        ProfileEntity save = repository.save(profileEntity);
        return modelMapper.map(save,ProfileDTO.class);
    }


    public ProfileDTO registrationTeacher(ProfileTeacherDTO dto) {
        Optional<ProfileEntity> entity = repository.findByLogin(dto.getLogin());
        if (entity.isPresent()) {
            log.error("AUTHSERVICE REGISTRATION TEACHER:  BUNDAY FOYDALANUVCHI MAVJUD (login) : {}", dto.getLogin());
            throw new BadRequestException("Bunday foydalanuvchi ro'yxatdan o'tgan!");
        }
        ProfileEntity profile = modelMapper.map(dto, ProfileEntity.class);
        profile.setGroups("null");
        profile.setRole(ProfileRole.ROLE_USER);
        profile.setStatus(ProfileStatus.BLOCK);
        profile.setDegree(ProfilePosition.USTOZ);
        ProfileEntity save = repository.save(profile);

        return modelMapper.map(save,ProfileDTO.class);

    }


    public ProfileDTO updateTeacher(String id, ProfileTeacherUpdateDTO dto) {
        ProfileEntity profileEntity = repository.findById(id).orElseThrow(() -> {
            log.error("AUTHSERVICE UPDATE TEACHER:  BUNDAY FOYDALANUVCHI MAVJUD EMAS(id) : {}", id);
            throw new ItemNotFoundException("Bunday foydalanuvchi mavjud emas!");
        });
        modelMapper.map(dto,profileEntity);
        profileEntity.setGroups("null");
        ProfileEntity save = repository.save(profileEntity);
        return modelMapper.map(save, ProfileDTO.class);
    }


}
