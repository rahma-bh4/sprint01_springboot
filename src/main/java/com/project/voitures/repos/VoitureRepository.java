package com.project.voitures.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.voitures.entities.Voiture;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {

}
