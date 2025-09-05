package com.competicaoPiorFilme.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutorInputDTO {
	
	@NotBlank
	private String nome;
	
}
