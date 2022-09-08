package com.company.dto;

import com.company.dto.base.BaseDTO;
import com.company.enums.ProfilePosition;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO extends BaseDTO {
    @JsonProperty("FACULTY")
    private String faculty;

    @JsonProperty("GROUP")
    private String groups;

    @JsonProperty("LOGIN")
    private String login;

    @JsonProperty("PASSWORD")
    private String password;

    @JsonProperty("SURNAME")
    private String surname;

    @JsonProperty("NAME")
    private String name;

    @JsonProperty("PHONE")
    private String phone;

    @JsonProperty("ROLE")
    private ProfileRole role;

    @JsonProperty("DEGREE")
    private ProfilePosition degree;

    @JsonProperty("STATUS")
    private ProfileStatus status;

    private String jwt;
}
