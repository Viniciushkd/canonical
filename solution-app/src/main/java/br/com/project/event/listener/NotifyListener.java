package br.com.project.event.listener;

import org.springframework.stereotype.Component;

import com.hazelcast.core.IQueue;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

import br.com.project.canonical.NotifyCanonical;
import br.com.project.event.Event;


@Component
public class NotifyListener implements MessageListener<Event<NotifyCanonical>>{

	private final IQueue<NotifyCanonical> notify;
	/**
	 * 
	 * @param notify
	 */
	public NotifyListener(IQueue<NotifyCanonical> notify) {
		this.notify = notify;
	}

	@Override
	public void onMessage(Message<Event<NotifyCanonical>> message) {
		try {
			pushNotify(message.getMessageObject());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param messageObject
	 * @throws InterruptedException
	 */
	private void pushNotify(Event<NotifyCanonical> messageObject) throws InterruptedException {
		notify.put(messageObject.getCanonical());
	}
}
