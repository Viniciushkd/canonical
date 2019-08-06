package br.com.project.builder;

import java.util.Date;

import br.com.project.dto.EntityDto;

public class EntityDtoBuilder {
private final EntityDto instance;
	
	public EntityDtoBuilder() {
		instance = new EntityDto();
	}

	public EntityDtoBuilder setFirstName(String firstName) {
		this.instance.setFirstName(firstName);
		return this;
	}

	public EntityDtoBuilder setLastName(String lastName) {
		this.instance.setLastName(lastName);
		return this;
	}

	public EntityDtoBuilder setAge(int age) {
		this.instance.setAge(age);
		return this;
	}

	public EntityDtoBuilder setDate(Date date) {
		this.instance.setDate(date);
		return this;
	}

	public EntityDtoBuilder setRequestId(String requestId) {
		this.instance.setRequestId(requestId);
		return this;
	}	
	
	public EntityDto build() {
		return instance;
	}
}
