package com.company.dto.request;

import com.company.annotation.Passport;
import com.company.annotation.Phone;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileTeacherDTO {

    @NotNull()
    @Size(min = 2, message = "Fakultetingizni tanlang!")
    private String faculty;


    private String groups;


    @Passport(message = "Login kiritilishda xatolik!")
    private String login;


    @NotNull()
    @Size(min = 8, message = "Parol kiritilishda xatolik, parol 8 ta belgidan kam bo'lmasligi kerak!")
    private String password;

    @NotNull()
    @Size(min = 3, message = "Familiya kiritilshda xatolik, belgilar soni 2 tadan ko'p bo'lish kerak!")
    private String surname;


    @NotNull()
    @Size(min = 2, message = "Ism kiritilshda xatolik, belgilar soni 2 tadan ko'p bo'lish kerak!")
    private String name;


    @Phone(message = "Telefon raqam kiritilishda xatolik!")
    private String phone;
}
