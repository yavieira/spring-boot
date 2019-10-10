package br.com.mySpringBoot.api.controller;

import br.com.mySpringBoot.api.controller.form.TopicoForm;
import br.com.mySpringBoot.api.controller.vo.TopicoVO;
import br.com.mySpringBoot.api.model.Topico;
import br.com.mySpringBoot.api.repository.CursoRepository;
import br.com.mySpringBoot.api.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoVO> lista(String nomeCurso) {

        if (nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoVO.convert(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoVO.convert(topicos);
        }
    }

    @PostMapping
    public ResponseEntity<TopicoVO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {

            Topico topico = form.converter(cursoRepository);
            topicoRepository.save(topico);
            URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(uri).body(new TopicoVO(topico));
        }
}
