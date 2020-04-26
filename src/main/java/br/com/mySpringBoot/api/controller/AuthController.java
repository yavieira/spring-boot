package br.com.mySpringBoot.api.controller;

import br.com.mySpringBoot.api.controller.form.LoginForm;
import br.com.mySpringBoot.api.controller.validation.ErroVO;
import br.com.mySpringBoot.api.controller.vo.TokenVO;
import br.com.mySpringBoot.api.model.Usuario;
import br.com.mySpringBoot.api.repository.AlbumRepository;
import br.com.mySpringBoot.api.repository.UsuarioRepository;
import br.com.mySpringBoot.api.security.SecurityConfig;
import br.com.mySpringBoot.api.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid LoginForm form) {

        UsernamePasswordAuthenticationToken dadosLogin = form.convert();
        try {
            Authentication auth = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(auth);
            return ResponseEntity.ok(new TokenVO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(new ErroVO("Bad Credentials", "User not found"));
        }
    }

    @RequestMapping(value = "login")
    public String login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {

        Usuario usuario = usuarioRepository.findByEmail(username).orElse(null);

        if (usuario == null) {
            return "redirect:/index";
        }

        boolean verified = new BCryptPasswordEncoder().matches(password, usuario.getSenha());
        if (verified) {
            return "redirect:home";
        }
        return "redirect:/index";
    }

    @RequestMapping(value = "home")
    public String home(Model model) {

        model.addAttribute("albums", albumRepository.findAll());
        return "home";
    }

    public static void main(String[] args) {
    }
}