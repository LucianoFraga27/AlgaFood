package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/testes")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	
	// QUERY STRINGS
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> consltapornome(@RequestParam String nome) {
		return cozinhaRepository.findByNome(nome);
		
	}
	
	@GetMapping("/cozinhas/por-letra")
	public List<Cozinha> consltaporletras(@RequestParam String nome) {
		return cozinhaRepository.findByNomeContaining(nome);
		
	}
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(@RequestParam BigDecimal taxaInicial,@RequestParam BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
		
	}
	
	@GetMapping("/restaurantes/por-nome-taxa-frete")
	public List<Restaurante> restaurantesPorNomeEFrete( String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.find(nome,taxaInicial, taxaFinal);
		
	}
	
	
	@GetMapping("/restaurantes/por-nome-cozinha")
	public List<Restaurante> restaurantesPorNomeECozinhaId(@RequestParam String nome,@RequestParam Long cozinhaId) {
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
		
	}
	
	
	@GetMapping("/restaurantes/por-nome-primeiro")
	public Optional<Restaurante> restauranteNomePrimeiro(@RequestParam String nome) {
		return restauranteRepository.findFirstByNomeContaining(nome);
		
	}

}
