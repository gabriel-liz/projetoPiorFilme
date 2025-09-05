package com.competicaoPiorFilme;

import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.competicaoPiorFilme.api.model.IntervalorEntrePremiosResponseDTO;
import com.competicaoPiorFilme.domain.model.Filme;
import com.competicaoPiorFilme.domain.repository.FilmeRepository;
import com.competicaoPiorFilme.domain.service.CsvImportService;
import com.competicaoPiorFilme.domain.service.IntervaloEntrePremiosService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class FilmeIntegrationTest {
	@Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private CsvImportService csvImportService;

    @Autowired
    private IntervaloEntrePremiosService intervaloService;

    @BeforeEach
    void setup() throws Exception {
        filmeRepository.deleteAll();
        InputStream inputStream = new ClassPathResource("filmes-teste.csv").getInputStream();
        csvImportService.importarCsv(inputStream);
    }

    @Test
    void deveCarregarFilmesPremiadosCorretamente() {
        List<Filme> filmes = filmeRepository.findAll();

        // Verifica se filmes foram importados
        assertThat(filmes).hasSize(4);

        // Valida se pelo menos um filme está premiado
        assertThat(filmes.stream().anyMatch(Filme::getPremiado)).isTrue();

        // Valida os títulos premiados
        assertThat(filmes.stream()
                .filter(Filme::getPremiado))
                .extracting(Filme::getTitulo)
                .containsExactlyInAnyOrder("Can't Stop the Music", "Mommie Dearest", "Filme D");
    }

    @Test
    void deveCalcularIntervalosEntrePremiosDosProdutores() {
        IntervalorEntrePremiosResponseDTO response = intervaloService.calcularIntervalorPremios();

        // Valida que existem registros mínimos e máximos
        assertThat(response.getMin()).isNotEmpty();
        assertThat(response.getMax()).isNotEmpty();

        // Validação de produtores específicos de acordo com CSV de teste
        assertThat(response.getMin().get(0).getProducer())
                .isEqualTo("Produtor 2");
        assertThat(response.getMax().get(0).getProducer())
                .isEqualTo("Produtor 2");

        // Validação dos intervalos (exemplo: não negativo)
        assertThat(response.getMin().get(0).getInterval()).isGreaterThanOrEqualTo(0);
        assertThat(response.getMax().get(0).getInterval()).isGreaterThanOrEqualTo(0);
    }
}

