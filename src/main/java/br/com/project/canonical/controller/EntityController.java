package br.com.project.canonical.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.canonical.business.EntityBusiness;
import br.com.project.canonical.dto.EntityDto;
import br.com.project.canonical.dto.ResponseDto;

@RestController
public class EntityController {

	@Autowired
	private EntityBusiness business;
	/**
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/save")
	public ResponseEntity<ResponseDto<EntityDto>> save(@RequestBody EntityDto dto) {
		ResponseDto<EntityDto> entityDto = business.save(dto);
		return new ResponseEntity<>(entityDto, HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<ResponseDto<Set<EntityDto>>> list() {
		ResponseDto<Set<EntityDto>> entityDto = business.list();
		return new ResponseEntity<>(entityDto, HttpStatus.OK);
	}
}
