package com.algaworks.algafood.jpaCozinha;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;


@Component
public class CadastroCozinha {
	
	@PersistenceContext
	private EntityManager manager;
	
	public java.util.List<Cozinha> listar(){
		TypedQuery<Cozinha> query = manager.createQuery("from Cozinha",Cozinha.class);
		return query.getResultList();
	}
	
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);	
	}
	
	
	@org.springframework.transaction.annotation.Transactional 
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	@Transactional
	public void remover(Cozinha cozinha) {
		cozinha = buscar(cozinha.getId());
		manager.remove(cozinha);
	}
	
}

