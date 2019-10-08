package br.com.mySpringBoot.api.controller;

import br.com.mySpringBoot.api.controller.vo.TopicoVO;
import br.com.mySpringBoot.api.model.Curso;
import br.com.mySpringBoot.api.model.Topico;
import br.com.mySpringBoot.api.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {

    @Autowired
    private TopicoRepository repository;

    @RequestMapping("/topicos")
    @ResponseBody
    public List<TopicoVO> lista(){

        List<Topico> topicos = repository.findAll();

        return TopicoVO.convert(topicos);
    }
}
