package com.company.dto.request;

import com.company.annotation.Passport;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ProfileLoginDTO {


    @JsonProperty("login")
    @Passport(message = "Seria xato. Mazgi!")
    private String login;


    @NotNull(message = "Password is required.")
    @Size(min = 8, message = "Password have to be more than 8 characters.")
    private String password;
}
