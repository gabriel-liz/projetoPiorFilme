package com.competicaoPiorFilme.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.competicaoPiorFilme.api.assembler.FilmeDTOAssembler;
import com.competicaoPiorFilme.api.assembler.FilmeInputDTODisassembler;
import com.competicaoPiorFilme.api.model.FilmeDTO;
import com.competicaoPiorFilme.api.model.input.FilmeInputDTO;
import com.competicaoPiorFilme.domain.model.Filme;
import com.competicaoPiorFilme.domain.repository.FilmeRepository;
import com.competicaoPiorFilme.domain.service.FilmeService;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private FilmeService filmeService;
	
	@Autowired
	private FilmeDTOAssembler filmeAssembler;
	
	@Autowired
	private FilmeInputDTODisassembler filmeInputDTODisassembler;

	public FilmeController(FilmeService filmeService, FilmeDTOAssembler filmeAssembler,
			FilmeInputDTODisassembler filmeInputDTODisassembler) {		
		this.filmeService = filmeService;
		this.filmeAssembler = filmeAssembler;
		this.filmeInputDTODisassembler = filmeInputDTODisassembler;
	}
	
	@GetMapping
	public List<FilmeDTO> listar(){
		List<Filme> todosFilmes = filmeRepository.findAll();
		return filmeAssembler.toCollectionDTO(todosFilmes);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FilmeDTO adicionar(@RequestBody @Valid FilmeInputDTO filmeInputDTO) {
		Filme filme = filmeInputDTODisassembler.toDomainObject(filmeInputDTO);
		filme = filmeService.salvar(filme);
		return filmeAssembler.toDTO(filme);
	}
	
	
	
	
}
