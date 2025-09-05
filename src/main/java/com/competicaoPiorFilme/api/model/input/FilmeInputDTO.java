package com.competicaoPiorFilme.api.model.input;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeInputDTO {
	
	private String titulo;
	private Integer ano;	
	private List<Long> produtoresIds;
	private List<Long> estudiosIds;
}
