package com.competicaoPiorFilme.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Filme {

	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false)
	private int ano;

	@ManyToMany
	@JoinTable(
			name = "filmeProdutor",
			joinColumns = @JoinColumn(name = "filme_id"),
			inverseJoinColumns = @JoinColumn(name= "produtor_id"))
	private List<Produtor> produtores;
	
	@ManyToMany
	@JoinTable(
			name = "filme_studio",
			joinColumns = @JoinColumn(name= "filme_id"),
			inverseJoinColumns = @JoinColumn(name = "studio_id"))	
	private List<Studio> studios;	
	
	private boolean premiado;
	
}
