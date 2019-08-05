package br.com.project.canonical.convert;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.project.canonical.build.EntityDtoBuilder;
import br.com.project.canonical.data.Entity;
import br.com.project.canonical.dto.EntityDto;

@Lazy
@Component
public class EntityToEntityDtoConverter implements Converter<Entity, EntityDto> {
		
	@Override
	public EntityDto convert(Entity source) {
		try {
			return new EntityDtoBuilder()
					.setFirstName(source.getFirstName())
					.setLastName(source.getLastName())
					.setAge(source.getAge())
					.setDate(source.getDate())
					.setRequestId(source.getRequestId())
					.build();
		} catch (Exception e) {
			return null;
		}
	}
}
