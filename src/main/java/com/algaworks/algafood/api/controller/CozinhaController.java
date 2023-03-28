package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cozinhaService;

	// Listando em JSON
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	// Buscando por ID
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
		if (cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());
		}
		return ResponseEntity.notFound().build();
	}

	// Adicionando JSON
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	// Editando
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
		if (cozinhaRepository.findById(cozinhaId).isPresent()) {
			cozinha.setId(cozinhaId);
			cozinha = cozinhaService.salvar(cozinha);
			return ResponseEntity.ok(cozinha);
		}
		return ResponseEntity.notFound().build();
	}

	// Excluindo
	/*
	 * @DeleteMapping("/{cozinhaId}") public ResponseEntity<Cozinha>
	 * remover(@PathVariable Long cozinhaId) { try {
	 * cozinhaService.excluir(cozinhaId); return ResponseEntity.noContent().build();
	 * 
	 * //} catch (EntidadeNaoEncontradaException e) { //return
	 * ResponseEntity.notFound().build();
	 * 
	 * } catch (EntidadeEmUsoException e) { return
	 * ResponseEntity.status(HttpStatus.CONFLICT).build(); } }
	 */

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {

		cozinhaService.excluir(cozinhaId);

	}
}
