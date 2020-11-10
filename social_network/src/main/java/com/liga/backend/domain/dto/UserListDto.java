package com.liga.backend.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {

    private UUID id;

    private String fullName;

    private int age;

    private char sex;

    private String interests;

    private String city;
}
