package br.com.project.canonical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.canonical.dto.EntityDto;
import br.com.project.canonical.dto.ResponseDto;
import br.com.project.canonical.service.EntityService;

@RestController
public class EntityController {

	@Autowired
	private EntityService service;
	
	@PostMapping("/post")
	public ResponseEntity<ResponseDto<EntityDto>> post(@RequestBody EntityDto dto) {
		ResponseDto<EntityDto> entityDto = service.convertEntity(dto);
		return new ResponseEntity<>(entityDto, HttpStatus.OK);
	}
}
