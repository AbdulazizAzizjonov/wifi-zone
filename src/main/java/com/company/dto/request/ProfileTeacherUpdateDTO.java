package com.company.dto.request;

import com.company.annotation.Passport;
import com.company.annotation.Phone;
import com.company.enums.ProfilePosition;
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
public class ProfileTeacherUpdateDTO {

    @NotNull(message = "Fakultetingizni tanlang!")
    private String faculty;


    @NotNull(message = "Lavozimingizni tanlang!")
    private ProfilePosition degree;


    @NotNull()
    @Size(min = 3, message = "Familiya kiritilshda xatolik, belgilar soni 2 tadan ko'p bo'lish kerak!")
    private String surname;


    @NotNull()
    @Size(min = 2, message = "Ism kiritilshda xatolik, belgilar soni 2 tadan ko'p bo'lish kerak!")
    private String name;


    @Phone(message = "Telefon raqam kiritilishda xatolik!")
    private String phone;


    //    @Passport(message = "Seria xato. Mazgi!")
    //    private String login;
    //    @NotNull(message = "Password is required.")
    //    @Size(min = 8, message = "Password have to be more than 8 characters.")
    //    private String password;
}
