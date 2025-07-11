package com.kzree.backend.input.dto;

import java.io.Serializable;
import java.util.Set;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import com.kzree.backend.common.dto.EntityDTO;
import com.kzree.backend.sector.dto.SectorDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InputDTO extends EntityDTO implements Serializable {
    @NotEmpty
    private String name;

    @AssertTrue
    private Boolean termsAccepted;

    @NotEmpty
    private Set<SectorDTO> sectors;
}
