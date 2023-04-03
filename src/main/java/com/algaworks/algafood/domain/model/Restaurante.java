package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable=false)
	private String nome;
	
	@PositiveOrZero
	@Column(name="taxa_frete",nullable=false)
	private BigDecimal taxaFrete;

	
	// @JsonIgnore
	// @JsonIgnoreProperties({"hibernateLazyInitializer"})
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.CozinhaID.class)
	@NotNull
	@ManyToOne // (fetch=FetchType.LAZY)
	@JoinColumn(name="cozinha_id",nullable=false)
	private Cozinha cozinha;
	
	@JsonIgnore 
	@Embedded		//essa propriedade é uma classe do tipo incoporada. 
	private Endereco endereco;
	
	@JsonIgnore
	@CreationTimestamp 			// Hibernate.annotations | 
	@Column(nullable=false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp		// toda vez que for alterado irá ser salvo
	@Column(nullable=false, columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;
	
	@JsonIgnore
	@ManyToMany // (fetch=FetchType.EAGER)
	@JoinTable(name="restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name="restaurante_id"),
			inverseJoinColumns = @JoinColumn(name="forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();
	
	
}
