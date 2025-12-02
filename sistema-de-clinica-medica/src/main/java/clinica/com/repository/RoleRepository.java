package clinica.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clinica.com.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByNome(String nome);
}
