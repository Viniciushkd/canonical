package br.com.project.canonical.service;

import java.util.List;

import br.com.project.canonical.canonical.EntityCanonical;
import br.com.project.canonical.data.Entity;

public interface IEntityService {
	
	public void save(EntityCanonical canonical);
	
	public List<Entity> list();
}
