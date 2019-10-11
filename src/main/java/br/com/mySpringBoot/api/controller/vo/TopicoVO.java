package br.com.mySpringBoot.api.controller.vo;

import br.com.mySpringBoot.api.model.Topico;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoVO {

    //Os VO's (Value Objects) são representações de uma entity para saída de dados da aplicação

    private Long id;

    private String titulo;

    private String mensagem;

    private LocalDateTime dataCriacao;

    private String nomeCurso;

    public TopicoVO(Topico topico){

        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.nomeCurso = topico.getCurso().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public static Page<TopicoVO> convert(Page<Topico> topicos) {

        return topicos.map(TopicoVO::new);
    }
}
