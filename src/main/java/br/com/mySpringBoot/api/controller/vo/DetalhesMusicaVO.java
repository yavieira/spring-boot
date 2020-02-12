package br.com.mySpringBoot.api.controller.vo;

import br.com.mySpringBoot.api.model.Musica;

import java.time.LocalDateTime;

public class DetalhesMusicaVO {

    private Long id;
    private String titulo;
    private String album;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public DetalhesMusicaVO (Musica musica) {
        this.id = musica.getId();
        this.titulo = musica.getTitulo();
        this.album = musica.getAlbum();
        this.dataCriacao = musica.getDataCriacao();
        this.nomeAutor = musica.getAutor().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

}
