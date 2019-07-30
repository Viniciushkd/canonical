package br.com.project.canonical.service;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.project.canonical.canonical.EntityCanonical;
import br.com.project.canonical.dto.EntityDto;
import br.com.project.canonical.dto.ResponseDto;

@Component
public class EntityService {

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
		
		final EntityDto entityDto = entityCanonicalToEntityDtoConverter.convert(canonical);
		final ResponseDto<EntityDto> response = new ResponseDto<>(entityDto);
		return response;
	}
	
	
}
