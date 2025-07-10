package com.kzree.backend.sector.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kzree.backend.sector.domain.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, UUID> {
}
