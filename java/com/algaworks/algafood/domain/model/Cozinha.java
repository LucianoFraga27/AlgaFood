package com.algaworks.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algaworks.algafood.core.validation.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonRootName("cozinha")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {
	
	@NotNull(groups = Groups.CozinhaID.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//@JsonIgnore
	@NotBlank
	@Column(nullable=false)
	private String nome;
	
	/*
	@Column(name="observacao",nullable = false)
	private String descricao;
	*/
	
	// Uma cozinha tem muitos restaurante
	@JsonIgnore // Para envitar essa propriedade para que não entre num loop circular
	@OneToMany(mappedBy="cozinha")  // Querendo saber qual nome do atributo mapeado em Restaurante
	private List<Restaurante> restaurantes = new ArrayList<>();
	
}