package com.kzree.backend.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.kzree.backend.IntegrationTest;
import com.kzree.backend.common.errors.ErrorCodes;
import com.kzree.backend.input.domain.Input;
import com.kzree.backend.input.dto.InputDTO;
import com.kzree.backend.input.repository.InputRepository;
import com.kzree.backend.sector.domain.Sector;
import com.kzree.backend.sector.mapper.SectorMapper;
import com.kzree.backend.sector.repository.SectorRepository;
import com.kzree.backend.util.Serialization;

import jakarta.persistence.EntityManager;

@IntegrationTest
@AutoConfigureMockMvc
public class InputControllerIT {
    static final String ENTITY_API_URL = "/api/inputs";

    @Autowired
    private InputRepository inputRepository;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private SectorMapper sectorMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void getAllInputs() throws Exception {
        var testName = "Test Input";

        var input = new Input();
        input.setName(testName);
        input.setTermsAccepted(true);

        var session = new MockHttpSession();
        var sessionId = session.getId();
        input.setOwner(sessionId);

        inputRepository.saveAndFlush(input);

        mockMvc
                .perform(get(ENTITY_API_URL)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNotEmpty())
                .andExpect(jsonPath("$.[0].name").value(testName))
                .andExpect(jsonPath("$.[0].termsAccepted").value(true))
                .andExpect(jsonPath("$.[0].createdAt").isNotEmpty())
                .andExpect(jsonPath("$.[0].modifiedAt").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @Transactional
    public void saveNewInputWithoutSectors() throws Exception {
        var testName = "New Input";

        var input = new InputDTO();
        input.setName(testName);
        input.setTermsAccepted(true);

        mockMvc
                .perform(post(ENTITY_API_URL)
                        .contentType("application/json")
                        .content(Serialization.serializeJSONFromObject(input)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ErrorCodes.VALIDATION_ERROR));
    }

    @Test
    @Transactional
    public void saveNewInputWithoutName() throws Exception {
        var sector = new Sector();
        sector.setName("Test Sector");
        var sectorDTO = sectorMapper.toDto(sectorRepository.saveAndFlush(sector));

        var input = new InputDTO();
        input.setName("");
        input.setTermsAccepted(true);
        input.setSectors(Set.of(sectorDTO));

        mockMvc
                .perform(post(ENTITY_API_URL)
                        .contentType("application/json")
                        .content(Serialization.serializeJSONFromObject(input)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ErrorCodes.VALIDATION_ERROR));
    }

    @Test
    @Transactional
    public void saveNewInputWithTermsNotAgreed() throws Exception {
        var sector = new Sector();
        sector.setName("Test Sector");
        var sectorDTO = sectorMapper.toDto(sectorRepository.saveAndFlush(sector));

        var input = new InputDTO();
        input.setName("Test Input");
        input.setTermsAccepted(false);
        input.setSectors(Set.of(sectorDTO));

        mockMvc
                .perform(post(ENTITY_API_URL)
                        .contentType("application/json")
                        .content(Serialization.serializeJSONFromObject(input)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ErrorCodes.VALIDATION_ERROR));
    }

    @Test
    @Transactional
    public void saveNewInput() throws Exception {
        var testName = "New Input";

        var sector = new Sector();
        sector.setName("Test Sector");
        var sectorDTO = sectorMapper.toDto(sectorRepository.saveAndFlush(sector));

        var input = new InputDTO();
        input.setName(testName);
        input.setTermsAccepted(true);
        input.setSectors(Set.of(sectorDTO));

        mockMvc
                .perform(post(ENTITY_API_URL)
                        .contentType("application/json")
                        .content(Serialization.serializeJSONFromObject(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(testName))
                .andExpect(jsonPath("$.termsAccepted").value(true))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.modifiedAt").isNotEmpty());
    }

    @Test
    @Transactional
    public void updateExistingInput() throws Exception {
        var testName = "Updated Input";

        var sector = new Sector();
        sector.setName("Test Sector");
        var sectorDTO = sectorMapper.toDto(sectorRepository.saveAndFlush(sector));

        var input = new Input();
        input.setName("Old Input");
        input.setTermsAccepted(true);
        input.setSectors(Set.of(sector));
        input = inputRepository.saveAndFlush(input);

        var inputDTO = new InputDTO();
        inputDTO.setId(input.getId());
        inputDTO.setName(testName);
        inputDTO.setTermsAccepted(true);
        inputDTO.setSectors(Set.of(sectorDTO));

        em.clear();
        em.flush();

        mockMvc
                .perform(put(ENTITY_API_URL + "/" + input.getId())
                        .contentType("application/json")
                        .content(Serialization.serializeJSONFromObject(inputDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(testName));
    }
}
