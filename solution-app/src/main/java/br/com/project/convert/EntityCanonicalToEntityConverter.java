package br.com.project.convert;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.project.builder.EntityDataBuilder;
import br.com.project.canonical.EntityCanonical;
import br.com.project.data.Entity;

@Lazy
@Component
public class EntityCanonicalToEntityConverter implements Converter<EntityCanonical, Entity> {
	
	@Override
	public Entity convert(EntityCanonical source) {
		try {
			return new EntityDataBuilder()
					.setFirstName(source.firstName())
					.setLastName(source.lastName())
					.setAge(source.age())
					.setDate(source.date())
					.setRequestId(source.requestId())
					.build();
		} catch (Exception e) {
			return null;
		}
	}

}
