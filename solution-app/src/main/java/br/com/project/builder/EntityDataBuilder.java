package br.com.project.builder;

import java.util.Date;

import br.com.project.data.Entity;

public class EntityDataBuilder {

	private final Entity instance;
	
	public EntityDataBuilder() {
		instance = new Entity();
	}

	public EntityDataBuilder setFirstName(String firstName) {
		this.instance.setFirstName(firstName);
		return this;
	}

	public EntityDataBuilder setLastName(String lastName) {
		this.instance.setLastName(lastName);
		return this;
	}

	public EntityDataBuilder setAge(int age) {
		this.instance.setAge(age);
		return this;
	}

	public EntityDataBuilder setDate(Date date) {
		this.instance.setDate(date);
		return this;
	}

	public EntityDataBuilder setRequestId(String requestId) {
		this.instance.setRequestId(requestId);
		return this;
	}	
	
	public Entity build() {
		return instance;
	}
}
