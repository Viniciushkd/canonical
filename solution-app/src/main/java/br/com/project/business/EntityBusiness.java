package br.com.project.business;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.project.canonical.EntityCanonical;
import br.com.project.data.Entity;
import br.com.project.dto.EntityDto;
import br.com.project.dto.ResponseDto;
import br.com.project.service.IEntityService;

@Component
public class EntityBusiness {
	
	@Autowired
	private IEntityService service;
	private final Converter<EntityDto, EntityCanonical> entityDtoToEntityCanonicalConverter;
	private final Converter<EntityCanonical, EntityDto> entityCanonicalToEntityDtoConverter;
	private final Converter<Entity, EntityDto> entityToEntityDtoConverter;
	/**
	 * 
	 * @param entityDtoToEntityCanonicalConverter
	 * @param entityCanonicalToEntityDtoConverter
	 */
	public EntityBusiness(
			final Converter<EntityDto, EntityCanonical> entityDtoToEntityCanonicalConverter,
			final Converter<EntityCanonical, EntityDto> entityCanonicalToEntityDtoConverter,
			final Converter<Entity, EntityDto> entityToEntityDtoConverter) {
		this.entityDtoToEntityCanonicalConverter = entityDtoToEntityCanonicalConverter;
		this.entityCanonicalToEntityDtoConverter = entityCanonicalToEntityDtoConverter;
		this.entityToEntityDtoConverter = entityToEntityDtoConverter;
	}
	/**
	 * 
	 * @param dto
	 * @return
	 */
	public ResponseDto<EntityDto> save(final EntityDto dto) {
		final EntityCanonical canonical = entityDtoToEntityCanonicalConverter.convert(dto);
		
		// ..
		
		this.service.save(canonical);
		
		// ..
		
		final EntityDto entityDto = entityCanonicalToEntityDtoConverter.convert(canonical);
		return new ResponseDto<>(entityDto);
	}
	
	public ResponseDto<Set<EntityDto>> list() {
		final Set<EntityDto> listDto = new HashSet<>() ;
		try {
			this.service.list()
						.stream()
						.forEach((e) -> {
							listDto.add(entityToEntityDtoConverter.convert(e));
						});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseDto<>(listDto);
	}

}
