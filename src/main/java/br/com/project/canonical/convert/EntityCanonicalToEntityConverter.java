package br.com.project.canonical.convert;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.project.canonical.build.EntityDataBuilder;
import br.com.project.canonical.canonical.EntityCanonical;
import br.com.project.canonical.data.Entity;

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
					.build();
		} catch (Exception e) {
			return null;
		}
	}

}
