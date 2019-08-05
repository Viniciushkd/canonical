package br.com.project.canonical.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.project.canonical.canonical.EntityCanonical;
import br.com.project.canonical.data.Entity;
import br.com.project.canonical.event.Event;
import br.com.project.canonical.event.Publisher;
import br.com.project.canonical.repository.EntityRepository;

@Component
@Transactional
public class EntityService implements IEntityService {
	
	@Autowired
	private Publisher<EntityCanonical> publisher;
	@Autowired
	private EntityRepository repository;

	/**
	 * 
	 */
	public void save(final EntityCanonical canonical) {
		this.publisher.publishEntity(new Event<EntityCanonical>(this, canonical));
	}
	/**
	 * 
	 */
	public List<Entity> list() {
		return this.repository.findAll();
	}
	
}
