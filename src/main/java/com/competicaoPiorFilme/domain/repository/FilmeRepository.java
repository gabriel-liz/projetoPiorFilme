package com.competicaoPiorFilme.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.competicaoPiorFilme.domain.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

}
