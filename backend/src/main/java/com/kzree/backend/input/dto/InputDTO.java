package com.kzree.backend.input.dto;

import java.io.Serializable;
import java.util.Set;

import com.kzree.backend.common.dto.EntityDTO;
import com.kzree.backend.sector.dto.SectorDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputDTO extends EntityDTO implements Serializable {
    private String name;
    private Boolean termsAccepted;
    private Set<SectorDTO> sectors;
}
