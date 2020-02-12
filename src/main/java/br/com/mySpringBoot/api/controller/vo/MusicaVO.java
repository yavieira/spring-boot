package br.com.mySpringBoot.api.controller.vo;

import br.com.mySpringBoot.api.model.Musica;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class MusicaVO {

    //Os VO's (Value Objects) são representações de uma entity para saída de dados da aplicação

    private Long id;

    private String titulo;

    private String album;

    private LocalDateTime dataCriacao;

    private String autor;

    public MusicaVO(Musica musica){

        this.id = musica.getId();
        this.titulo = musica.getTitulo();
        this.album = musica.getAlbum();
        this.autor = musica.getAutor().getNome();
        this.dataCriacao = musica.getDataCriacao();
    }

    public MusicaVO(String titulo, String album, String autor) {
        this.titulo = titulo;
        this.album = album;
        this.autor = autor;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public static MusicaVO convert(Musica musica) {

        return new MusicaVO(musica);
    }
}
