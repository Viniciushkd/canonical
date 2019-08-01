package br.com.project.canonical.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.project.canonical.canonical.EntityCanonical;
import br.com.project.canonical.data.Entity;
import br.com.project.canonical.repository.EntityRepository;

@Component
public class EntityListener {

	@Autowired
	private EntityRepository repository;
	private final Converter<EntityCanonical, Entity> entityCanonicalToEntityConverter;
	
	public EntityListener(Converter<EntityCanonical, Entity> entityCanonicalToEntityConverter) {
		this.entityCanonicalToEntityConverter = entityCanonicalToEntityConverter;
	}

	private void save(EntityCanonical canonical) {
		final Entity entity = entityCanonicalToEntityConverter.convert(canonical);
		repository.save(entity);
	}
	
	@Async
	@EventListener
	public void handleEntityEvent(EntityEvent event) {
		save(event.getCanonical());
	}
	
}
