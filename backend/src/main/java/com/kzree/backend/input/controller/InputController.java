package com.kzree.backend.input.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kzree.backend.common.errors.ResourceNotFoundException;
import com.kzree.backend.input.dto.InputDTO;
import com.kzree.backend.input.service.InputService;

import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<List<InputDTO>> getInputs(HttpServletRequest request) {
        log.info("Request to get all inputs");
        var sessionId = request.getSession().getId();
        var inputs = inputService.getInputs(sessionId);
        return ResponseEntity.ok(inputs);
    }

    @PostMapping
    public ResponseEntity<InputDTO> saveInput(@Valid @RequestBody InputDTO inputDTO, HttpServletRequest request) {
        log.info("Request to save new input");
        var sessionId = request.getSession().getId();
        var savedInput = inputService.save(inputDTO, sessionId);
        return ResponseEntity.ok(savedInput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InputDTO> updateInput(@Valid @RequestBody InputDTO inputDTO, @PathVariable UUID id,
            HttpServletRequest request) {
        log.info("Request to update input with ID: {}", id);
        var sessionId = request.getSession().getId();
        var updatedInput = inputService.update(inputDTO, sessionId);
        if (updatedInput.isEmpty()) {
            throw new ResourceNotFoundException("Input not found with ID: " + id);
        }

        return ResponseEntity.ok(updatedInput.get());
    }
}
