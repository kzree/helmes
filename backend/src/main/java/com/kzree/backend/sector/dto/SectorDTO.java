package com.kzree.backend.sector.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kzree.backend.common.dto.EntityDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SectorDTO extends EntityDTO implements Serializable {
    private String name;

    private Set<SectorDTO> subSectors;
}
