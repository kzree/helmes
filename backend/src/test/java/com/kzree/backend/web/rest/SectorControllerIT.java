package com.kzree.backend.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.kzree.backend.IntegrationTest;
import com.kzree.backend.sector.domain.Sector;
import com.kzree.backend.sector.repository.SectorRepository;

import jakarta.persistence.EntityManager;

@IntegrationTest
@AutoConfigureMockMvc
public class SectorControllerIT {
    static final String ENTITY_API_URL = "/api/sectors";

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EntityManager em;

    @BeforeEach
    public void initTest() {
        sectorRepository.deleteAll();
    }

    @Test
    @Transactional
    public void getAllSectors() throws Exception {
        var testName = "Test Sector";

        var sector1 = new Sector();
        sector1.setName(testName);
        sectorRepository.saveAndFlush(sector1);

        var sector2 = new Sector();
        sector2.setName("Another Sector");
        sector2.setParentSector(sector1);
        sectorRepository.saveAndFlush(sector2);

        mockMvc
                .perform(get(ENTITY_API_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNotEmpty())
                .andExpect(jsonPath("$.[0].name").value(testName))
                .andExpect(jsonPath("$.[0].createdAt").isNotEmpty())
                .andExpect(jsonPath("$.[0].modifiedAt").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(1));
    }
}
