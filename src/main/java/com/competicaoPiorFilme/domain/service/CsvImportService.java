package com.competicaoPiorFilme.domain.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.competicaoPiorFilme.domain.model.Estudio;
import com.competicaoPiorFilme.domain.model.Filme;
import com.competicaoPiorFilme.domain.model.Produtor;
import com.competicaoPiorFilme.domain.repository.EstudioRepository;
import com.competicaoPiorFilme.domain.repository.FilmeRepository;
import com.competicaoPiorFilme.domain.repository.ProdutorRepository;

@Service
public class CsvImportService {

	@Autowired
	private FilmeRepository filmeRepository;

	@Autowired
	private ProdutorRepository produtorRepository;

	@Autowired
	private EstudioRepository estudioRepository;

	public CsvImportService(FilmeRepository filmeRepository, ProdutorRepository produtorRepository,
			EstudioRepository estudioRepository) {
		this.filmeRepository = filmeRepository;
		this.produtorRepository = produtorRepository;
		this.estudioRepository = estudioRepository;
	}

	public void importarCsv(MultipartFile file) throws Exception {
		try (BufferedReader fileReader = new BufferedReader(
				new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

			CSVFormat format = CSVFormat.DEFAULT.builder()
					.setHeader()
					.setSkipHeaderRecord(true)
					.setDelimiter(';')
					.build();

			try (CSVParser csvParser = new CSVParser(fileReader, format)) {
				for (CSVRecord record : csvParser) {
					Integer ano = Integer.valueOf(record.get("year").trim());
					String titulo = record.get("title").trim();
					String studiosStr = record.get("studios").trim();
					String producersStr = record.get("producers").trim();

					Optional<Filme> existente = filmeRepository.findByTituloAndAno(titulo, ano);
					Filme filme = existente.orElseGet(() -> {
						Filme novo = new Filme();
						novo.setAno(ano);
						novo.setTitulo(titulo);
						return novo;
					});

					Set<Estudio> estudios = new HashSet<>();
					for (String nomeEstudio : studiosStr.split(",")) {
						String nome = nomeEstudio.trim();
						if (nome.isEmpty())
							continue;

						Estudio estudio = estudioRepository.findByNome(nome)
								.orElseGet(() -> {
							Estudio novo = new Estudio();
							novo.setNome(nome);
							return estudioRepository.save(novo);
						});
						estudios.add(estudio);
					}
					filme.setEstudios(estudios);

					Set<Produtor> produtores = new HashSet<>();
					for (String nomeProdutor : producersStr.split(",")) {
						String nome = nomeProdutor.trim();
						if (nome.isEmpty())
							continue;

						Produtor produtor = produtorRepository.findByNome(nome)
								.orElseGet(() -> {
							Produtor novo = new Produtor();
							novo.setNome(nome);
							return produtorRepository.save(novo);
						});
						produtores.add(produtor);
					}
					filme.setProdutores(produtores);

					filmeRepository.save(filme);
				}
			}
		}
	}
}
