package clinica.com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import clinica.com.entity.Usuario;
import clinica.com.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
    
    @Autowired
    protected UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + email);
        }

        var authorities = usuario.getRoles().stream()
                .map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(role.getNome()))
                .collect(Collectors.toList());

        return new User(usuario.getEmail(), usuario.getSenha(), authorities);
    }

    public Usuario save(Usuario u) {
    	
    	if (!u.getSenha().startsWith("$2a$")) {
			u.setSenha(passwordEncoder.encode(u.getSenha()));
    	}
        return usuarioRepository.save(u);
    }

    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}