package com.kzree.backend.sector.dto;

import java.io.Serializable;

import com.kzree.backend.common.dto.EntityDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectorDTO extends EntityDTO implements Serializable {
    private String name;
}
