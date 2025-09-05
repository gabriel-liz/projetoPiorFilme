package com.competicaoPiorFilme.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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

	private boolean premiado;
	 
	@ManyToMany
	@JoinTable(
			name = "filmeProdutor",
			joinColumns = @JoinColumn(name = "filme_id"),
			inverseJoinColumns = @JoinColumn(name= "produtor_id"))
	private Set<Produtor> produtores = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
			name = "filme_studio",
			joinColumns = @JoinColumn(name= "filme_id"),
			inverseJoinColumns = @JoinColumn(name = "studio_id"))	
	private Set<Estudio> estudios = new HashSet<>();

	public boolean getPremiado() {
		return premiado;
	}
	
}
