package com.competicaoPiorFilme.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competicaoPiorFilme.api.model.IntervaloEntrePremiosDTO;
import com.competicaoPiorFilme.api.model.IntervalorEntrePremiosResponseDTO;
import com.competicaoPiorFilme.domain.model.Filme;
import com.competicaoPiorFilme.domain.repository.FilmeRepository;

@Service
public class IntervaloEntrePremiosService {

    @Autowired
    private FilmeRepository filmeRepository;

    public IntervaloEntrePremiosService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public IntervalorEntrePremiosResponseDTO calcularIntervalorPremios() {
        // pega apenas os filmes vencedores
        List<Filme> filmesPremiados = filmeRepository.findAll().stream()
                .filter(Filme::isWinner)
                .collect(Collectors.toList());

        // agrupa anos de vitória por produtor
        Map<String, List<Integer>> premiosPorProdutor = filmesPremiados.stream()
                .flatMap(f -> f.getProdutores()
                        .stream()
                        .map(p -> new Object[] { p.getNome(), f.getAno() }))
                .collect(Collectors.groupingBy(
                        obj -> (String) obj[0],
                        Collectors.mapping(obj -> (Integer) obj[1], Collectors.toList())
                ));

        List<IntervaloEntrePremiosDTO> intervalos = new ArrayList<>();

        // calcula intervalos por produtor
        for (Map.Entry<String, List<Integer>> entry : premiosPorProdutor.entrySet()) {
            String produtor = entry.getKey();
            List<Integer> anos = entry.getValue();

            if (anos.size() < 2) continue;

            Collections.sort(anos);

            for (int i = 1; i < anos.size(); i++) {
                int intervalo = anos.get(i) - anos.get(i - 1);
                intervalos.add(new IntervaloEntrePremiosDTO(
                        produtor,
                        intervalo,
                        anos.get(i - 1),
                        anos.get(i)
                ));
            }
        }

        if (intervalos.isEmpty()) {
            return new IntervalorEntrePremiosResponseDTO(List.of(), List.of());
        }

        // menor intervalo
        int minIntervalo = intervalos.stream()
                .mapToInt(IntervaloEntrePremiosDTO::getInterval)
                .min()
                .orElse(0);

        // maior intervalo
        int maxIntervalo = intervalos.stream()
                .mapToInt(IntervaloEntrePremiosDTO::getInterval)
                .max()
                .orElse(0);

        List<IntervaloEntrePremiosDTO> min = intervalos.stream()
                .filter(i -> i.getInterval() == minIntervalo)
                .collect(Collectors.toList());

        List<IntervaloEntrePremiosDTO> max = intervalos.stream()
                .filter(i -> i.getInterval() == maxIntervalo)
                .collect(Collectors.toList());

        return new IntervalorEntrePremiosResponseDTO(min, max);
    }
}
