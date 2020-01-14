package br.com.mySpringBoot.api.controller;

import br.com.mySpringBoot.api.controller.form.MusicaForm;
import br.com.mySpringBoot.api.controller.vo.DetalhesMusicaVO;
import br.com.mySpringBoot.api.controller.vo.MusicaVO;
import br.com.mySpringBoot.api.model.Musica;
import br.com.mySpringBoot.api.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/musica")
public class MusicasController {

    @Autowired
    private MusicaRepository musicaRepository;

    @GetMapping
    public ResponseEntity<MusicaVO> get(@RequestBody String titulo) {

            Musica musica = musicaRepository.findByTitulo(titulo);

            if(musica == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(MusicaVO.convert(musica));
    }

    @PostMapping
    @Transactional
//    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<MusicaVO> cadastrar(@RequestBody @Valid MusicaForm form, UriComponentsBuilder uriBuilder) {

        Musica musica = form.converter(form);
        musicaRepository.save(musica);
        URI uri = uriBuilder.path("/musica/{id}").buildAndExpand(musica.getId()).toUri();
        return ResponseEntity.created(uri).body(new MusicaVO(musica));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesMusicaVO> detalhar(@PathVariable Long id) {

        Optional<Musica> topico = musicaRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DetalhesMusicaVO(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {

        Optional<Musica> optional = musicaRepository.findById(id);
        if (optional.isPresent()) {
            musicaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}