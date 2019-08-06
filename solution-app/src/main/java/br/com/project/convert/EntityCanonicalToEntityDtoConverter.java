package br.com.project.convert;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.project.builder.EntityDtoBuilder;
import br.com.project.canonical.EntityCanonical;
import br.com.project.dto.EntityDto;

@Lazy
@Component
public class EntityCanonicalToEntityDtoConverter implements Converter<EntityCanonical, EntityDto> {

	@Override
	public EntityDto convert(EntityCanonical source) {
		try {
			return new EntityDtoBuilder()
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
