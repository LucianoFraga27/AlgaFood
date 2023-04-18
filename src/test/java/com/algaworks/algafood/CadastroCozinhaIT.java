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
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

import com.algaworks.algafood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		databaseCleaner.clearTables();
		prepararDados();
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
	
	private void prepararDados() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Americana");
		cozinhaRepository.save(cozinha2);
		
		Cozinha cozinha3 = new Cozinha();
		cozinha3.setNome("Brasileira");
		cozinhaRepository.save(cozinha3);
		
		Cozinha cozinha4 = new Cozinha();
		cozinha4.setNome("Chinesa");
		cozinhaRepository.save(cozinha4);
	}
	
}
