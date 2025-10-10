package clinica.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import clinica.com.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByEmail(String email);
    Paciente findByTelefone(String telefone);
}