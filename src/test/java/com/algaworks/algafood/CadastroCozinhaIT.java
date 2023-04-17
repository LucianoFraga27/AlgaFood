package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@SpringBootTest
class CadastroCozinhaIT {

	@Autowired
	CadastroCozinhaService cadastroCozinhaService;

	// Happy Path
	@Test
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		// || cenario ||

		// Instanciando cozinha
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		// || ação ||
		novaCozinha = cadastroCozinhaService.salvar(novaCozinha);

		// || validação ||

		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();

	}

	// Unhappy path
	@Test
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		// || cenario ||

		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("");

		// || ação ||

		ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
			cadastroCozinhaService.salvar(novaCozinha);
		});

		// || validação ||

		assertThat(erroEsperado).isNotNull(); // validando exception

	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		
		EntidadeEmUsoException erroEsperado = Assertions.assertThrows(EntidadeEmUsoException.class,
				() -> {
					cadastroCozinhaService.excluir(1L);
					}
				);
		
		assertThat(erroEsperado).isNotNull();
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		
		CozinhaNaoEncontradaException erroEsperado = Assertions.assertThrows(CozinhaNaoEncontradaException.class,
				() -> {
					cadastroCozinhaService.excluir(100L);
					}
				);
		
		assertThat(erroEsperado).isNotNull();
	}
	

}
