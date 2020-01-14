package br.com.mySpringBoot.api.security;

import br.com.mySpringBoot.api.model.Usuario;
import br.com.mySpringBoot.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Usuario> user = usuarioRepository.findByEmail(email);
            if(user.isPresent()){
                return user.get();
            }
        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
