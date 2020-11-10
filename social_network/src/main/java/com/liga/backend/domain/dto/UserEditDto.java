package com.liga.backend.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEditDto {

    private UUID id;

    @NotEmpty
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String firstName;

    @NotEmpty
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String lastName;

    private LocalDate birthDay;

    @NotEmpty
    private char sex;

    private String interests;

    private String city;

    private @Singular
    Set<UUID> friends;

    private @Singular
    Set<UUID> friendOfs;

}
