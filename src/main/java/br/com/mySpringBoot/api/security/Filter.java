package br.com.mySpringBoot.api.security;

import br.com.mySpringBoot.api.model.Usuario;
import br.com.mySpringBoot.api.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Filter extends OncePerRequestFilter {

    //Em classes do tipo Filter não conseguimos fazer injeção de dependências

    private TokenService tokenService;

    private UsuarioRepository usuarioRepository;

    public Filter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        //Injeção via construtor
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valid = tokenService.isTokenValid(token);
        if(valid){
            autenticar(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticar(String token) {
        Long id = tokenService.getIdUsuario(token);
        Usuario usuario = usuarioRepository.findById(id).get();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String recuperarToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){

            return null;
        }
        return token.substring(7, token.length());
    }
}
