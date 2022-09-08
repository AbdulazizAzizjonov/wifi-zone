package com.company.repository;

import com.company.dto.ProfileDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;



import java.util.Optional;

public interface    ProfileRepository extends JpaRepository<ProfileEntity,String> {
    Optional<ProfileEntity> findByLogin(String login);

    Optional<ProfileEntity> findById(String id);

    Iterable<ProfileEntity> findByStatus(ProfileStatus status);

    @Transactional
    @Modifying
    @Query("update ProfileEntity p set p.status = ?1 where p.id = ?2")
    int updateStatusById(ProfileStatus status, String id);


}

