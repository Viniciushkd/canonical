package br.com.project.canonical.event;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.project.canonical.canonical.EntityCanonical;
import br.com.project.canonical.data.Entity;
import br.com.project.canonical.repository.EntityRepository;

@Component
@Transactional
public class EntityListener {

	@Autowired
	private EntityRepository repository;
	private final Converter<EntityCanonical, Entity> entityCanonicalToEntityConverter;
	/**
	 * 
	 * @param entityCanonicalToEntityConverter
	 */
	public EntityListener(Converter<EntityCanonical, Entity> entityCanonicalToEntityConverter) {
		this.entityCanonicalToEntityConverter = entityCanonicalToEntityConverter;
	}
	/**
	 * 
	 * @param canonical
	 */
	private void save(EntityCanonical canonical) {
		final Entity entity = entityCanonicalToEntityConverter.convert(canonical);
		repository.save(entity);
	}
	/**
	 * 
	 * @param event
	 */
	@Async
	@EventListener
	public void handleEntityEvent(Event<EntityCanonical> event) {
		save(event.getCanonical());
	}
	
}
