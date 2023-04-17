package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RestauranteNaoEncontradaException extends NegocioException {

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Restaurante de código '%d' não foi encontrado";
	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	public RestauranteNaoEncontradaException(Long estado_id) {
		this(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, estado_id));
	}
}
