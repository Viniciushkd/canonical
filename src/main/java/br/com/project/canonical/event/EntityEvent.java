package br.com.project.canonical.event;

import org.springframework.context.ApplicationEvent;

import br.com.project.canonical.canonical.EntityCanonical;

public class EntityEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityCanonical canonical;

	public EntityEvent(Object source, EntityCanonical canonical) {
		super(source);
		this.canonical = canonical;
	}

	public EntityCanonical getCanonical() {
		return canonical;
	}
}
