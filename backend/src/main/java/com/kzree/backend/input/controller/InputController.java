package com.kzree.backend.input.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kzree.backend.input.dto.InputDTO;
import com.kzree.backend.input.service.InputService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/inputs")
@RequiredArgsConstructor
public class InputController {
    private final InputService inputService;

    @GetMapping
    public ResponseEntity<List<InputDTO>> getInputs() {
        log.info("Request to get all inputs");
        var inputs = inputService.getInputs();
        return ResponseEntity.ok(inputs);
    }

    @PostMapping
    public ResponseEntity<InputDTO> saveInput(@Valid @RequestBody InputDTO inputDTO) {
        log.info("Request to save new input");
        var savedInput = inputService.save(inputDTO);
        return ResponseEntity.ok(savedInput);
    }

    @PutMapping
    public ResponseEntity<InputDTO> updateInput(@Valid @RequestBody InputDTO inputDTO) {
        log.info("Request to update input with ID: {}", inputDTO.getId());
        var updatedInput = inputService.update(inputDTO);
        return ResponseEntity.ok(updatedInput);
    }
}
