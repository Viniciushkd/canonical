package br.com.project.canonical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.project.canonical.canonical.EntityCanonical;
import br.com.project.canonical.dto.EntityDto;
import br.com.project.canonical.dto.ResponseDto;
import br.com.project.canonical.event.EntityEvent;
import br.com.project.canonical.event.Publisher;

@Component
public class EntityService {
	
	@Autowired
	private Publisher publisher;

	private final Converter<EntityDto, EntityCanonical> entityDtoToEntityCanonicalConverter;
	private final Converter<EntityCanonical, EntityDto> entityCanonicalToEntityDtoConverter;

	public EntityService(
			final Converter<EntityDto, EntityCanonical> entityDtoToEntityCanonicalConverter,
			final Converter<EntityCanonical, EntityDto> entityCanonicalToEntityDtoConverter) {
		this.entityDtoToEntityCanonicalConverter = entityDtoToEntityCanonicalConverter;
		this.entityCanonicalToEntityDtoConverter = entityCanonicalToEntityDtoConverter;
	}
	
	public ResponseDto<EntityDto> convertEntity(final EntityDto dto) {
		final EntityCanonical canonical = entityDtoToEntityCanonicalConverter.convert(dto);
		
		// ...
		
		publisher.publishEntity(new EntityEvent(this, canonical));
		
		// ...
		
		final EntityDto entityDto = entityCanonicalToEntityDtoConverter.convert(canonical);
		return new ResponseDto<>(entityDto);
	}
	
}
