package br.com.mySpringBoot.api.controller.vo;

import br.com.mySpringBoot.api.model.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoVO {

    private Long id;

    private String titulo;

    private String mensagem;

    private LocalDateTime dataCriacao;

    public TopicoVO(Topico topico){

        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }

    public static List<TopicoVO> convert(List<Topico> topicos) {

        return topicos.stream().map(TopicoVO::new).collect(Collectors.toList());
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
}
