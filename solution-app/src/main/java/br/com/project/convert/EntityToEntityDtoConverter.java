package br.com.project.convert;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.project.build.EntityDtoBuilder;
import br.com.project.data.Entity;
import br.com.project.dto.EntityDto;

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
