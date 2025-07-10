package com.kzree.backend.input.mapper;

import org.mapstruct.Mapper;

import com.kzree.backend.common.mapper.EntityMapper;
import com.kzree.backend.input.domain.Input;
import com.kzree.backend.input.dto.InputDTO;

import jakarta.persistence.Entity;

@Mapper(componentModel = "spring")
public interface InputMapper extends EntityMapper<InputDTO, Input> {
}
