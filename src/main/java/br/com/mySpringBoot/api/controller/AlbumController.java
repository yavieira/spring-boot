package br.com.mySpringBoot.api.controller;

import br.com.mySpringBoot.api.model.Album;
import br.com.mySpringBoot.api.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @RequestMapping("/album/{id}")
    public String getAlbumById(@PathVariable Long id, Model model) {

        Album album = albumRepository.findById(id).orElse(null);

        model.addAttribute("album", album);

            return "album";
    }
}
