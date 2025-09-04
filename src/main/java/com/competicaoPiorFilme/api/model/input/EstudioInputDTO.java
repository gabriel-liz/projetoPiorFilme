package com.competicaoPiorFilme.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudioInputDTO {
	
	@NotBlank
	private String nome;

}
