package br.com.mySpringBoot.api.controller;

import br.com.mySpringBoot.api.controller.form.AtualizacaoTopicoForm;
import br.com.mySpringBoot.api.controller.form.TopicoForm;
import br.com.mySpringBoot.api.controller.vo.DetalhesTopicoVO;
import br.com.mySpringBoot.api.controller.vo.TopicoVO;
import br.com.mySpringBoot.api.model.Topico;
import br.com.mySpringBoot.api.repository.CursoRepository;
import br.com.mySpringBoot.api.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.cache.annotation.CacheEvict;
// import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
//    @Cacheable(value = "listaTopicos")
    //Por boa prática, utiliza-se cache em tabelas que nunca ou raramente são atualizadas, para evitar o CacheEvict que gera processamento
    public Page<TopicoVO> lista(@RequestParam(required = false) String nomeCurso,
                                @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable p) {

        if (nomeCurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(p);
            return TopicoVO.convert(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, p);
            return TopicoVO.convert(topicos);
        }
    }

    @PostMapping
    @Transactional
//    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<TopicoVO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {

        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoVO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicoVO> detalhar(@PathVariable Long id) {

        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DetalhesTopicoVO(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
//    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<TopicoVO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoVO(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
//    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity remover(@PathVariable Long id) {

        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}