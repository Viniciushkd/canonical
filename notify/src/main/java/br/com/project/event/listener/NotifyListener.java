package br.com.project.event.listener;

import org.springframework.stereotype.Component;

import br.com.project.canonical.NotifyCanonical;
import br.com.project.data.Notify;
import br.com.project.event.Event;
import br.com.project.repository.INotifyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.convert.converter.Converter;

@Component
public class NotifyListener {

	@Autowired
	private INotifyRepository repository;
	private final Converter<NotifyCanonical, Notify> notifyCanonicalToNotifyConverter;
	
	public NotifyListener(Converter<NotifyCanonical, Notify> notifyCanonicalToNotifyConverter) {
		this.notifyCanonicalToNotifyConverter = notifyCanonicalToNotifyConverter;
	}

	@EventListener
	public void handleNotifyEvent(Event<NotifyCanonical> event) {
		final Notify notify = notifyCanonicalToNotifyConverter.convert(event.getCanonical());
		repository.save(notify);
	}
}
