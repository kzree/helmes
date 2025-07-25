package com.kzree.backend.input.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kzree.backend.input.domain.Input;

@Repository
public interface InputRepository extends JpaRepository<Input, UUID> {
    List<Input> findAllByOwner(String ownerId);

    Optional<Input> findByIdAndOwner(UUID id, String ownerId);
}
