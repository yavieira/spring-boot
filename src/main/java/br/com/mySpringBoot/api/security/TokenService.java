package br.com.mySpringBoot.api.security;

import br.com.mySpringBoot.api.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String gerarToken(Authentication auth) {

        Usuario logado = (Usuario) auth.getPrincipal();

        Date hoje = new Date();
        long exp = Long.parseLong(expiration);
        Date dataExpiracao = new Date(hoje.getTime() + exp);

        return Jwts.builder()
                .setIssuer("Spring-Boot API")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
