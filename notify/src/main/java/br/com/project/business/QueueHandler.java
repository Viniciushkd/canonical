package br.com.project.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.hazelcast.core.IQueue;

import br.com.project.canonical.NotifyCanonical;
import br.com.project.event.Event;
import br.com.project.event.Publisher;
import br.com.project.event.QueueEvent;
/**
 * 
 * @author TinoTech:marcos.santos
 *
 */
@Component
public class QueueHandler {

	@Autowired
	private Publisher<NotifyCanonical> publisher;
	private final IQueue<NotifyCanonical> queue;
	/**
	 * 
	 * @param mailQueue
	 */
	public QueueHandler(IQueue<NotifyCanonical> mailQueue) {
		this.queue = mailQueue;
	}
	/**
	 * 
	 * @param event
	 * @throws InterruptedException
	 */
	@Async
	@Retryable(maxAttempts = 3, value = Exception.class, backoff = @Backoff(delay = 3000))
	@EventListener
	public void handleNewItemInQueueEvent(final QueueEvent event) throws InterruptedException {
		final NotifyCanonical notify = queue.take();
		pushNotifyEvent(notify);
	}
	/**
	 * 
	 * @param notify
	 */
	private void pushNotifyEvent(final NotifyCanonical notify) {
		final Event<NotifyCanonical> event = new Event<>(this, notify);
		publisher.publishEntity(event);
	}
}
