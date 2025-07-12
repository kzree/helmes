package com.kzree.backend.input.service;

import java.util.List;
import java.util.Optional;

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
    public List<InputDTO> getInputs(String sessionId) {
        log.info("Fetching all inputs");
        var inputs = inputRepository.findAllByOwner(sessionId);
        return inputMapper.toDto(inputs);
    }

    @Transactional
    public InputDTO save(InputDTO inputDTO, String sessionId) {
        log.info("Saving new input");
        var inputEntity = inputMapper.toEntity(inputDTO);
        inputEntity.setOwner(sessionId);
        return inputMapper.toDto(inputRepository.save(inputEntity));
    }

    @Transactional
    public Optional<InputDTO> update(InputDTO inputDTO) {
        log.info("Updating input with ID: {}", inputDTO.getId());
        return inputRepository.findById(inputDTO.getId())
                .map(existingInput -> {
                    inputMapper.partialUpdate(existingInput, inputDTO);
                    return existingInput;
                })
                .map(inputRepository::save)
                .map(inputMapper::toDto);
    }
}
