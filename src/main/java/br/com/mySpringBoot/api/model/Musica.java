package br.com.mySpringBoot.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String album;
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne
    private Usuario usuario;

    public Musica(){

    }

    public Musica(String titulo, String autor, String album) {
        this.titulo = titulo;
        this.autor = autor;
        this.album = album;
    }

    public Musica(String titulo, String autor, String album, LocalDateTime dataCriacao, Usuario usuario) {
        this.titulo = titulo;
        this.autor = autor;
        this.album = album;
        this.dataCriacao = dataCriacao;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
