package clinica.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import clinica.com.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {}