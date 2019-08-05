package br.com.project.canonical.convert;

import java.util.Date;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.project.canonical.canonical.EntityCanonical;
import br.com.project.canonical.dto.EntityDto;

@Lazy
@Component
public class EntityDtoToEntityCanonicalConverter implements Converter<EntityDto, EntityCanonical> {

	@Override
	public EntityCanonical convert(EntityDto source) {
		try {
			return EntityCanonical
					.builder()
					.date(new Date())
					.age(source.getAge())
					.lastName(source.getLastName())
					.firstName(source.getFirstName())
					.requestId(UUID.randomUUID().toString())
					.build();
		} catch (final Exception e) {
			return null;
		}
	}

}
