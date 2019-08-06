package br.com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.project.canonical.EntityCanonical;
import br.com.project.data.Entity;
import br.com.project.event.Event;
import br.com.project.event.Publisher;
import br.com.project.repository.EntityRepository;

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
		final Event<EntityCanonical> event = new Event<>(this, canonical);
		this.publisher.publishEntity(event);
	}
	/**
	 * 
	 */
	public List<Entity> list() {
		return this.repository.findAll();
	}
	
}
