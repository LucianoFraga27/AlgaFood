package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CozinhaNaoEncontradaException extends NegocioException {

	private static final String MSG_COZINHA_NAO_ENCONTRADA = "Cozinha de código '%d' não foi encontrada";
	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	public CozinhaNaoEncontradaException(Long cozinhaId) {
		this(String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId));
	}
}
