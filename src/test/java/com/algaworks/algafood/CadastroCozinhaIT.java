package com.algaworks.algafood;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;

import javax.validation.ConstraintViolationException;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
	}
	
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinha() {

		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		

		given().accept(ContentType.JSON).when().get().then().statusCode(200);
	}

	@Test
	public void deveConter5Cozinhas_QuandoConsultarCozinha() {

		enableLoggingOfRequestAndResponseIfValidationFails();

		given().basePath("/cozinhas").port(port).accept(ContentType.JSON).when().get().then().body("", hasSize(10))
				.body("nome", Matchers.hasItems("Tailandesa", "Chinesa"));
	}
}
