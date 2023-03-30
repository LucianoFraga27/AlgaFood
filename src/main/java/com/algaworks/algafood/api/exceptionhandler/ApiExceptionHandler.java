package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ EntidadeNaoEncontradaException.class })
	public ResponseEntity<?> handleEstadoNaoEncontradoException(WebRequest request, EntidadeNaoEncontradaException ex) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

	}

	@ExceptionHandler({ EntidadeEmUsoException.class })
	public ResponseEntity<?> handleEntidadeEmUsoException(WebRequest request, EntidadeEmUsoException ex) {

		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);

	}

	@ExceptionHandler({ NegocioException.class })
	public ResponseEntity<?> handleNegocioException(WebRequest request, NegocioException ex) {

		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = Problem.builder()
					.status(status.value())
					.title(status.getReasonPhrase())
					.build();
		} else if (body instanceof String) {
			body = Problem.builder()
					.status(status.value())
					.title( (String) body )
					.build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problem
				.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
	}
	
}
