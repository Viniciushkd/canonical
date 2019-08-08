package br.com.project.event.listener;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.convert.converter.Converter;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.hazelcast.core.ITopic;

import br.com.project.canonical.EntityCanonical;
import br.com.project.canonical.NotifyCanonical;
import br.com.project.data.ETransactionType;
import br.com.project.data.Entity;
import br.com.project.event.Event;
import br.com.project.repository.EntityRepository;

@Component
@Transactional
public class EntityListener {

	@Autowired
	private EntityRepository repository;
	private final ITopic<Event<NotifyCanonical>> topc;
	private final Converter<EntityCanonical, Entity> entityCanonicalToEntityConverter;
	/**
	 * 
	 * @param entityCanonicalToEntityConverter
	 */
	public EntityListener(Converter<EntityCanonical, Entity> entityCanonicalToEntityConverter, 
			ITopic<Event<NotifyCanonical>> topc) {
		this.entityCanonicalToEntityConverter = entityCanonicalToEntityConverter;
		this.topc = topc;
	}
	/**
	 * 
	 * @param event
	 */
	private void save(Event<EntityCanonical> event) {
		final Entity entity = this.entityCanonicalToEntityConverter.convert(event.getCanonical());
		try {
			repository.save(entity);
			topc.publish(notifyTypeSave(event.getCanonical()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param source
	 * @return
	 */
	private Event<NotifyCanonical> notifyTypeSave(EntityCanonical source) {
		return new Event<NotifyCanonical>(this, 
				NotifyCanonical
					.builder()
					.t(source)
					.dateInsert(new Date())
					.type(ETransactionType.SAVE)
					.build());
	}
	/**
	 * 
	 * @param event
	 */
	@Async
	@Retryable(maxAttempts = 5, value = Exception.class, backoff = @Backoff(delay = 3000))
	@EventListener
	public void handleEntityEvent(Event<EntityCanonical> event) {
		save(event);
	}
}
