package clinica.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import clinica.com.entity.Prontuario;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    Prontuario findByPacienteId(Long pacienteId);
}