package com.kzree.backend.sector.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kzree.backend.sector.dto.SectorDTO;
import com.kzree.backend.sector.service.SectorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/sectors")
@RequiredArgsConstructor
public class SectorController {
    private final SectorService sectorService;

    @GetMapping
    public ResponseEntity<List<SectorDTO>> getAllSectors() {
        log.info("Reuqest to get all sectors");
        var sectors = sectorService.getAllSectors();
        return ResponseEntity.ok(sectors);
    }
}
