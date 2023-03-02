package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{
	
	// Quero apenas o vlaor que está entre esses valores das variaveis
	List<Restaurante> findByTaxaFreteBetween (BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	// encontrando por letras e pelo Id da Cozinha relacionada a esse restaurante
	//List<Restaurante> findByNomeContainingAndCozinhaId (String nome, Long cozinha);
	
	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNome (String nome,@Param("id") Long cozinhaId);
	
	
	Optional<Restaurante> findFirstByNomeContaining(String nome);
	
}
