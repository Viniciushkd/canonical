package br.com.project.event;

import org.springframework.context.ApplicationEvent;

public class Event<T> extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T obj;

	public Event(Object source, T obj) {
		super(source);
		this.obj = obj;
	}

	public T getCanonical() {
		return obj;
	}
}
