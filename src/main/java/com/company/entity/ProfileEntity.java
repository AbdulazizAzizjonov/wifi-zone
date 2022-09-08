package com.company.entity;

import com.company.entity.base.BaseEntity;
import com.company.entity.base.BaseUUIDEntity;
import com.company.enums.ProfilePosition;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table (name = "profile")
public class ProfileEntity extends BaseUUIDEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Column(nullable = false)
    private String faculty;

    @Column
    private String groups;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfilePosition degree;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;


}
