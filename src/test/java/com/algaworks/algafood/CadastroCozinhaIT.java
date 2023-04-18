package com.algaworks.algafood;


import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		flyway.migrate();
	}
	
	@Test
	void deveRetornarStatus200_QuandoConsultarCozinha() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.given()
				   		.accept(ContentType.JSON)
				   	.when()
				   		.get()
				   	.then()
				   		.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveConterDeterminadasCozinhas_QuandoConsultarCozinha() {
		
		String cozinha_1 = "Chinesa";
		String cozinha_2 = "Tailandesa";
		
		enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.given()
						.accept(ContentType.JSON)
					.when()
						.get()
					.then()
						.body("nome", Matchers.hasItems(cozinha_1,cozinha_2));
						
	}
	
	
	
}
