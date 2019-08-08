package br.com.project.event;

import org.springframework.context.ApplicationEvent;
/**
 * 
 * @author TinoTech:marcos.santos
 *
 */
public class QueueEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 79412184456811703L;
	/**
	 * 
	 * @param source
	 */
	public QueueEvent(Object source) {
		super(source);
	}

}
