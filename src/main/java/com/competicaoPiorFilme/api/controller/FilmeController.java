package com.competicaoPiorFilme.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.competicaoPiorFilme.api.assembler.FilmeDTOAssembler;
import com.competicaoPiorFilme.api.assembler.FilmeInputDTODisassembler;
import com.competicaoPiorFilme.api.model.FilmeDTO;
import com.competicaoPiorFilme.api.model.input.FilmeInputDTO;
import com.competicaoPiorFilme.domain.model.Filme;
import com.competicaoPiorFilme.domain.repository.FilmeRepository;
import com.competicaoPiorFilme.domain.service.CsvImportService;
import com.competicaoPiorFilme.domain.service.FilmeService;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

	private final FilmeRepository filmeRepository;
	private final FilmeService filmeService;
	private final FilmeDTOAssembler filmeAssembler;
	private final FilmeInputDTODisassembler filmeInputDTODisassembler;
	private final CsvImportService csvImportService;

	public FilmeController(FilmeRepository filmeRepository, FilmeService filmeService, FilmeDTOAssembler filmeAssembler,
			FilmeInputDTODisassembler filmeInputDTODisassembler, CsvImportService csvImportService) {
		this.filmeRepository = filmeRepository;
		this.filmeService = filmeService;
		this.filmeAssembler = filmeAssembler;
		this.filmeInputDTODisassembler = filmeInputDTODisassembler;
		this.csvImportService = csvImportService;
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
	
	@PostMapping(value = "/importar", consumes = "multipart/form-data")
	public ResponseEntity<String> importarCsv(@RequestParam("file") MultipartFile file) {
		try {
			csvImportService.importarCsv(file); 
//			return ResponseEntity.ok().build();
			return ResponseEntity.ok("Importação realizada com sucesso");
		} catch (Exception e) {
//			return ResponseEntity
//			        .status(HttpStatus.BAD_REQUEST)
//			        .body(null); // Ou use uma mensagem de erro		
			 e.printStackTrace(); // ou logger.error(...)
		        return ResponseEntity.badRequest().body("Erro ao importar CSV: " + e.getMessage());
		}
	}	
	
}
