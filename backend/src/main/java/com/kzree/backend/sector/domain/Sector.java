package com.kzree.backend.sector.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kzree.backend.common.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sector")
public class Sector extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "parentSector" }, allowSetters = true)
    @JoinColumn(name = "parent_id")
    private Sector parentSector;

    @OneToMany(mappedBy = "parentSector", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "parentSector" }, allowSetters = true)
    private Set<Sector> subSectors;
}
