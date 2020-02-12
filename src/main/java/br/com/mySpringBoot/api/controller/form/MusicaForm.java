package br.com.mySpringBoot.api.controller.form;

import br.com.mySpringBoot.api.controller.vo.MusicaVO;
import br.com.mySpringBoot.api.model.Autor;
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
    private Autor autor;

    public MusicaForm(@NotNull @NotEmpty String titulo, @NotNull @NotEmpty String album, @NotNull @NotEmpty Autor autor) {
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public static Musica converter(MusicaForm form) {

        Autor autor = new Autor();
        autor.setNome(form.getAutor().getNome());
        autor.setBanda(form.getAutor().getBanda());
        autor.setEstilo(form.getAutor().getEstilo());


        return new Musica(form.getTitulo(), autor, form.getAlbum());
    }
}
