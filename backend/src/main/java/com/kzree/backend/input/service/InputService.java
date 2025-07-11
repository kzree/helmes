package com.kzree.backend.input.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kzree.backend.input.dto.InputDTO;
import com.kzree.backend.input.mapper.InputMapper;
import com.kzree.backend.input.repository.InputRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InputService {
    private final InputRepository inputRepository;
    private final InputMapper inputMapper;

    @Transactional(readOnly = true)
    public List<InputDTO> getInputs() {
        log.info("Fetching all inputs");
        var inputs = inputRepository.findAll();
        return inputMapper.toDto(inputs);
    }

    @Transactional
    public InputDTO save(InputDTO inputDTO) {
        log.info("Saving new input");
        var inputEntity = inputMapper.toEntity(inputDTO);
        return inputMapper.toDto(inputRepository.save(inputEntity));
    }

    @Transactional
    public InputDTO update(InputDTO inputDTO) {
        log.info("Updating input with ID: {}", inputDTO.getId());
        log.debug("Input details: {}", inputDTO);
        var input = inputMapper.toEntity(inputDTO);
        input = inputRepository.save(input);
        return inputMapper.toDto(input);
    }
}
