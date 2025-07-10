package com.kzree.backend.common.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityDTO {
    private UUID id;

    private ZonedDateTime createdAt;

    private ZonedDateTime modifiedAt;
}
