package br.com.mySpringBoot.api.controller;

import br.com.mySpringBoot.api.controller.form.UsuarioForm;
import br.com.mySpringBoot.api.controller.vo.UsuarioVO;
import br.com.mySpringBoot.api.model.Endereco;
import br.com.mySpringBoot.api.model.Usuario;
import br.com.mySpringBoot.api.repository.UsuarioRepository;
import br.com.mySpringBoot.api.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    UsuarioRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioVO> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder){

        Usuario user = new Usuario();
        user.setNome(form.getNome());
        user.setEmail(form.getEmail());
        user.setSenha(new BCryptPasswordEncoder().encode(form.getSenha()));

        userRepository.save(user);

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UsuarioVO(user));
    }

    @PostMapping("/add")
    public ModelAndView add(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "cep") String cep,
            @RequestParam(value = "cidade") String cidade,
            @RequestParam(value = "uf") String uf,
            @RequestParam(value = "password") String password){

        if(userRepository.findByEmail(username).isPresent())
        {
            return new ModelAndView("userForm");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(firstName + " " + lastName);
        usuario.setEmail(username);
        usuario.setSenha(new BCryptPasswordEncoder().encode(password));

        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        endereco.setUf(uf);

        usuario.setEndereco(endereco);

        userRepository.save(usuario);

        return new ModelAndView("home");
    }

    public static void main(String[] args) {

        String chave = "35191110299567000679570000000000161242490938";
        String numeroCTe = chave.substring(25, 34);
        System.out.println(numeroCTe);

    }
}