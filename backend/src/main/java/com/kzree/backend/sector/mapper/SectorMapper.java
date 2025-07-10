package com.kzree.backend.sector.mapper;

import org.mapstruct.Mapper;

import com.kzree.backend.common.mapper.EntityMapper;
import com.kzree.backend.sector.domain.Sector;
import com.kzree.backend.sector.dto.SectorDTO;

@Mapper(componentModel = "spring")
public interface SectorMapper extends EntityMapper<SectorDTO, Sector> {
}
