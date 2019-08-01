package br.com.project.canonical.repository;

import org.springframework.stereotype.Repository;

import br.com.project.canonical.data.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EntityRepository extends JpaRepository<Entity, Long> {
	
}
