package br.com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.data.Notify;

@Repository
public interface INotifyRepository extends JpaRepository<Notify, Long> {

}
