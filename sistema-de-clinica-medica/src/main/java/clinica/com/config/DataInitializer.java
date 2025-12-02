package clinica.com.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import clinica.com.repository.RoleRepository;
import clinica.com.repository.UsuarioRepository;
import clinica.com.entity.Role;
import clinica.com.entity.Usuario;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByNome("ROLE_ADMIN") == null) {
                Role roleAdmin = new Role();
                roleAdmin.setNome("ROLE_ADMIN");
                roleRepository.save(roleAdmin);
            }

            if (!usuarioRepository.existsByEmail("admin@gmail.com")) {
                Usuario admin = new Usuario();
                admin.setNome("Administrador");
                admin.setEmail("admin@gmail.com");
                admin.setSenha(new BCryptPasswordEncoder().encode("123"));
                admin.setRoles(List.of(roleRepository.findByNome("ROLE_ADMIN")));
                usuarioRepository.save(admin);
            }
        };
    }
}
