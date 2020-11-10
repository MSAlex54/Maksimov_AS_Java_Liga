package com.liga.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    @NotEmpty
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String firstName;

    @NotEmpty
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String lastName;

    @NotEmpty
    private char sex;

}
