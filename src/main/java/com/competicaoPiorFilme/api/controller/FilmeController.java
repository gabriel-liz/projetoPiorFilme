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
import com.competicaoPiorFilme.api.model.IntervalorEntrePremiosResponseDTO;
import com.competicaoPiorFilme.api.model.input.FilmeInputDTO;
import com.competicaoPiorFilme.domain.model.Filme;
import com.competicaoPiorFilme.domain.repository.FilmeRepository;
import com.competicaoPiorFilme.domain.service.CsvImportService;
import com.competicaoPiorFilme.domain.service.FilmeService;
import com.competicaoPiorFilme.domain.service.IntervaloEntrePremiosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/filmes")
@RequiredArgsConstructor 
public class FilmeController {

    private final FilmeRepository filmeRepository;
    private final FilmeService filmeService;
    private final FilmeDTOAssembler filmeAssembler;
    private final FilmeInputDTODisassembler filmeInputDTODisassembler;
    private final CsvImportService csvImportService;
    private final IntervaloEntrePremiosService intervaloEntrePremiosService;

    @GetMapping
    public List<FilmeDTO> listar() {
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
            return ResponseEntity.ok("Importação realizada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao importar CSV: " + e.getMessage());
        }
    }

    @GetMapping("/premios/intervalo")
    public ResponseEntity<IntervalorEntrePremiosResponseDTO> buscarIntervalosPremios() {
        IntervalorEntrePremiosResponseDTO response = intervaloEntrePremiosService.calcularIntervalorPremios();
        return ResponseEntity.ok(response);
    }
}
