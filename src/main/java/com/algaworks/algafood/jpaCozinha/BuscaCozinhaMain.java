package com.algaworks.algafood.jpaCozinha;

import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApplication;
import com.algaworks.algafood.domain.model.Cozinha;

public class BuscaCozinhaMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		Cozinha cozinha = cadastroCozinha.buscar(100L);
		
		try {
			System.out.println("--------> "+cozinha.getNome());
		} catch (Exception e) {
			System.err.println("Id não válido");
		}
		
		
	}
	
}
