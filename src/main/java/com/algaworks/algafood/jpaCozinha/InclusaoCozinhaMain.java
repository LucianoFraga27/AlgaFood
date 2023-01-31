package com.algaworks.algafood.jpaCozinha;

import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApplication;
import com.algaworks.algafood.domain.model.Cozinha;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Portuguesa");
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Africana");
		
		Cozinha cozinha3 = new Cozinha();
		cozinha3.setNome("Colombiana");
		
		cozinha1 = cadastroCozinha.salvar(cozinha1);
		cozinha2 = cadastroCozinha.salvar(cozinha2);
		cozinha3 = cadastroCozinha.salvar(cozinha3);
		
		cadastroCozinha.salvar(cozinha1);
		System.out.println(cozinha1.getId() + " - " + cozinha1.getNome());
		
		cadastroCozinha.salvar(cozinha2);
		System.out.println(cozinha2.getId() + " - " + cozinha2.getNome());
		
		cadastroCozinha.salvar(cozinha3);
		System.out.println(cozinha3.getId() + " - " + cozinha3.getNome());
		}
		
}
