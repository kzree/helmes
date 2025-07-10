package com.kzree.backend.sector.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kzree.backend.sector.dto.SectorDTO;
import com.kzree.backend.sector.mapper.SectorMapper;
import com.kzree.backend.sector.repository.SectorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SectorService {
    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;

    public List<SectorDTO> getAllSectors() {
        log.info("Fetching all sectors");
        var sectors = sectorRepository.findAll();
        return sectorMapper.toDto(sectors);
    }
}
