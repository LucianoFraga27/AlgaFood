package com.algaworks.algafood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean // O spring não deve instanciar uma implementação para essa interface
public interface CustomJpaRepository<T,ID> extends JpaRepository<T, ID>{
	// tipo da entidade T, ID da entidade
	
	Optional<T> buscarPrimeiro();
	
}
