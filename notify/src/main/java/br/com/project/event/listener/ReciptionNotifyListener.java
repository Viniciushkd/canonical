/**
 *
 */
package br.com.project.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;

import br.com.project.canonical.NotifyCanonical;
import br.com.project.event.QueueEvent;

/**
 * 
 * @author TinoTech:marcos.santos
 *
 */
@Component
public class ReciptionNotifyListener implements ItemListener<NotifyCanonical> {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void itemAdded(final ItemEvent<NotifyCanonical> item) {
		pushNotificationNewItemInQueueEvent();
	}
	
	private void pushNotificationNewItemInQueueEvent() {
		final QueueEvent newItemInQueueEvent = new QueueEvent(this);
		applicationEventPublisher.publishEvent(newItemInQueueEvent);
	}

	@Override
	public void itemRemoved(final ItemEvent<NotifyCanonical> item) {
		
	}
}