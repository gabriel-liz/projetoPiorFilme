package com.competicaoPiorFilme.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.competicaoPiorFilme.domain.model.Studio;

public interface StudioRepository extends JpaRepository<Studio, Long> {

}
