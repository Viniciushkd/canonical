package br.com.project.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.business.EntityBusiness;
import br.com.project.dto.EntityDto;
import br.com.project.dto.ResponseDto;

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
		final ResponseDto<EntityDto> entityDto = this.business.save(dto);
		return new ResponseEntity<>(entityDto, HttpStatus.OK);
	}
	/**
	 * 
	 * @return
	 */
	@GetMapping("/list")
	public ResponseEntity<ResponseDto<Set<EntityDto>>> list() {
		final ResponseDto<Set<EntityDto>> entityDto = this.business.list();
		return new ResponseEntity<>(entityDto, HttpStatus.OK);
	}
}
