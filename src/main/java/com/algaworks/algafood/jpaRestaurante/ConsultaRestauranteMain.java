package com.algaworks.algafood.jpaRestaurante;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import lombok.AllArgsConstructor;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
	
		RestauranteRepository repository = applicationContext.getBean(RestauranteRepository.class);
		
		List<Restaurante> restaurantes = repository.listar();
		
		for (Restaurante restaurante : restaurantes) {
			System.out.printf("%s  -  %f  -  %s \n",
					restaurante.getNome(),
					restaurante.getTaxaFrete(),
					restaurante.getCozinha().getNome());
		}
		
	}
	
}
