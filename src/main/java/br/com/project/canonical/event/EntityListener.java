package br.com.project.canonical.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

	@Async
	@EventListener
	public void handleEntityEvent(EntityEvent event) {
		System.out.println(event.getCanonical().firstName());
	}
}
