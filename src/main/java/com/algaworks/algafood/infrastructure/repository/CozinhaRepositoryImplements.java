package com.algaworks.algafood.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImplements implements CozinhaRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public java.util.List<Cozinha> listar(){
		TypedQuery<Cozinha> query = manager.createQuery("from Cozinha",Cozinha.class);
		return query.getResultList();
	}
	
	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);	
	}
	
	
	@org.springframework.transaction.annotation.Transactional 
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	@Transactional
	@Override
	public void remover(Cozinha cozinha) {
		cozinha = buscar(cozinha.getId());
		manager.remove(cozinha);
	}
	
}
