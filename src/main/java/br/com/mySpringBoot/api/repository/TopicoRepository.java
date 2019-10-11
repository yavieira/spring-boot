package br.com.mySpringBoot.api.repository;

import br.com.mySpringBoot.api.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    //Para criar um método de acesso ao banco personalizado, basta nomear o método com o nome do atributo
    //Em casos do atributo for de uma classe de relacionamento, basta nomear a classe depois o atributo.
    // "CursoNome" é referente ao atributo "nome" da classe "Curso" Underline pode ser utilizado pra explicitar os casos.
    Page<Topico> findByCursoNome(String nomeCurso, Pageable pageable);
}
