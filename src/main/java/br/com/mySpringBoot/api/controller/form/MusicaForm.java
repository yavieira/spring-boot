package br.com.mySpringBoot.api.controller.form;

import br.com.mySpringBoot.api.controller.vo.MusicaVO;
import br.com.mySpringBoot.api.model.Musica;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MusicaForm {

    @NotNull @NotEmpty
    private String titulo;
    @NotNull @NotEmpty
    private String album;
    @NotNull @NotEmpty
    private String autor;

    public MusicaForm(@NotNull @NotEmpty String titulo, @NotNull @NotEmpty String album, @NotNull @NotEmpty String autor) {
        this.titulo = titulo;
        this.album = album;
        this.autor = autor;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public static Musica converter(MusicaForm form) {

        return new Musica(form.getTitulo(), form.getAutor(), form.getAlbum());
    }
}
