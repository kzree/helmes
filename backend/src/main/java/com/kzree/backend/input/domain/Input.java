package com.kzree.backend.input.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.kzree.backend.common.domain.BaseEntity;
import com.kzree.backend.sector.domain.Sector;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "input")
public class Input extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "terms_accepted", nullable = false)
    private Boolean termsAccepted = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rel_sector__input", joinColumns = @JoinColumn(name = "input_id"), inverseJoinColumns = @JoinColumn(name = "sector_id"))
    private Set<Sector> sectors = new HashSet<>();

    public void addSector(Sector sector) {
        sectors.add(sector);
    }

    public void removeSector(Sector sector) {
        sectors.remove(sector);
    }
}
