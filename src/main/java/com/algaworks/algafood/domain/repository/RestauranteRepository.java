package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurante;

public interface RestauranteRepository {

	// Orientado a collections
	
	List<Restaurante> listar();
	Restaurante buscar(Long id);
	Restaurante cadastrar(Restaurante restaurante);
	void remover(Restaurante restaurante);
	
}
