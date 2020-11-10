package com.liga.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friendship {

    private UUID userUUID;
    private UUID[] friendsUUIDs;

}
