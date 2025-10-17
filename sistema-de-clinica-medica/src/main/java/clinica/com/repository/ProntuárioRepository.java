package clinica.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import clinica.com.entity.Prontuário;

public interface ProntuárioRepository extends JpaRepository<Prontuário, Long> {
    Prontuário findByPacienteId(Long pacienteId);
}