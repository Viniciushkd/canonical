package br.com.project.service;

import java.util.List;

import br.com.project.canonical.EntityCanonical;
import br.com.project.data.Entity;

public interface IEntityService {
	
	public void save(EntityCanonical canonical);
	
	public List<Entity> list();
}
