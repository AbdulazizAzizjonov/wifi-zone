package com.company.service;

import com.company.dto.ProfileDTO;
import com.company.dto.request.ProfileStudentDTO;
import com.company.dto.request.ProfileStudentUpdateDTO;
import com.company.dto.request.ProfileTeacherDTO;
import com.company.dto.request.ProfileTeacherUpdateDTO;
import com.company.dto.response.ApiResponseDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfilePosition;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ProfileService extends GeneralService<ProfileRepository> {

    public ProfileService(ProfileRepository repository) {
        super(repository);
    }

    public ProfileDTO createStudentByAdmin(ProfileStudentDTO dto) {
        Optional<ProfileEntity> entity = repository.findByLogin(dto.getLogin());
        if (entity.isPresent()) {
            throw new BadRequestException("Bunday foydalanuvchi mavjud!");
        }
        ProfileEntity profile = modelMapper.map(dto, ProfileEntity.class);
        profile.setRole(ProfileRole.ROLE_USER);
        profile.setStatus(ProfileStatus.BLOCK);
        profile.setDegree(ProfilePosition.TALABA);
        ProfileEntity save = repository.save(profile);

        return modelMapper.map(save, ProfileDTO.class);
    }

    public ProfileDTO getProfileFullInfo(String login) {

        Optional<ProfileEntity> optional = repository.findByLogin(login);
        if (optional.isEmpty()) {
            throw new BadRequestException("Bunday foydalanuvchi mavjud emas!");
        }

        ProfileEntity profileEntity = optional.get();
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setRole(profileEntity.getRole());
        profileDTO.setFaculty(profileEntity.getFaculty());
        profileDTO.setGroups(profileEntity.getGroups());
        profileDTO.setLogin(profileEntity.getLogin());
        profileDTO.setPassword(profileEntity.getPassword());
        profileDTO.setSurname(profileEntity.getSurname());
        profileDTO.setName(profileEntity.getName());
        profileDTO.setPhone(profileEntity.getPhone());
        profileDTO.setDegree(profileEntity.getDegree());
        profileDTO.setStatus(profileEntity.getStatus());
        profileDTO.setId(profileEntity.getId());

        return profileDTO;
    }

    public List<ProfileDTO> getList() {
        Iterable<ProfileEntity> all = repository.findAll();
        List<ProfileDTO> dtoList = new LinkedList<>();
        all.forEach(profileEntity -> {
            ProfileDTO dto = new ProfileDTO();
            dto.setId(profileEntity.getId());
            dto.setDegree(profileEntity.getDegree());
            dto.setFaculty(profileEntity.getFaculty());
            dto.setGroups(profileEntity.getGroups());
            dto.setLogin(profileEntity.getLogin());
            dto.setPassword(profileEntity.getPassword());
            dto.setSurname(profileEntity.getSurname());
            dto.setName(profileEntity.getName());
            dto.setPhone(profileEntity.getPhone());
            dto.setStatus(profileEntity.getStatus());
            dtoList.add(dto);
        });

        return dtoList;
    }

    public List<ProfileDTO> getListProfileStatusBlock() {
        Iterable<ProfileEntity> all = repository.findByStatus(ProfileStatus.BLOCK);
        List<ProfileDTO> dtoList = new LinkedList<>();
        all.forEach(profileEntity -> {
            ProfileDTO dto = new ProfileDTO();
            dto.setId(profileEntity.getId());
            dto.setDegree(profileEntity.getDegree());
            dto.setFaculty(profileEntity.getFaculty());
            dto.setGroups(profileEntity.getGroups());
            dto.setLogin(profileEntity.getLogin());
            dto.setPassword(profileEntity.getPassword());
            dto.setSurname(profileEntity.getSurname());
            dto.setName(profileEntity.getName());
            dto.setPhone(profileEntity.getPhone());
            dto.setStatus(profileEntity.getStatus());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public List<ProfileDTO> getListProfileStatusActive() {
        Iterable<ProfileEntity> all = repository.findByStatus(ProfileStatus.ACTIVE);
        List<ProfileDTO> dtoList = new LinkedList<>();
        all.forEach(profileEntity -> {
            ProfileDTO dto = new ProfileDTO();
            dto.setId(profileEntity.getId());
            dto.setDegree(profileEntity.getDegree());
            dto.setFaculty(profileEntity.getFaculty());
            dto.setGroups(profileEntity.getGroups());
            dto.setLogin(profileEntity.getLogin());
            dto.setPassword(profileEntity.getPassword());
            dto.setSurname(profileEntity.getSurname());
            dto.setName(profileEntity.getName());
            dto.setPhone(profileEntity.getPhone());
            dto.setStatus(profileEntity.getStatus());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public ApiResponseDTO changeStatus(String profileId) {

        ProfileEntity entity = get(profileId);

        Function<ProfileStatus, Integer> changeStatuses = status -> {
            if (status.equals(ProfileStatus.ACTIVE)) {
                return repository.updateStatusById(ProfileStatus.BLOCK, profileId);
            } else {
                return repository.updateStatusById(ProfileStatus.ACTIVE, profileId);
            }
        };
        Integer apply = changeStatuses.apply(entity.getStatus());
        if (apply == 0) {
            return new ApiResponseDTO(false, "failed");
        }
        return new ApiResponseDTO(true, "success");
    }

    public ProfileEntity get(String id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Foydalanuvchi topilmadi!");
        });
    }


    public ProfileDTO updateStudentByAdmin(String id, ProfileStudentUpdateDTO dto) {
        ProfileEntity profileEntity = repository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Foydalanuvchi topilmadi!");
        });
        modelMapper.map(dto, profileEntity);
        ProfileEntity save = repository.save(profileEntity);
        return modelMapper.map(save, ProfileDTO.class);
    }


    public ProfileDTO createTeacherByAdmin(ProfileTeacherDTO dto) {
        Optional<ProfileEntity> entity = repository.findByLogin(dto.getLogin());
        if (entity.isPresent()) {
            throw new BadRequestException("Bunday foydalanuvchi mavjud");
        }
        ProfileEntity profile = modelMapper.map(dto, ProfileEntity.class);
        profile.setRole(ProfileRole.ROLE_USER);
        profile.setStatus(ProfileStatus.BLOCK);
        profile.setDegree(ProfilePosition.USTOZ);
        ProfileEntity save = repository.save(profile);

        return modelMapper.map(save, ProfileDTO.class);

    }


    public ProfileDTO updateTeacherByAdmin(String id, ProfileTeacherUpdateDTO dto) {
        ProfileEntity profileEntity = repository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Foydalanuvchi topilmadi!");
        });
        modelMapper.map(dto, profileEntity);
        ProfileEntity save = repository.save(profileEntity);
        return modelMapper.map(save, ProfileDTO.class);
    }

}
