package br.com.mySpringBoot.api.repository;

import br.com.mySpringBoot.api.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

}
