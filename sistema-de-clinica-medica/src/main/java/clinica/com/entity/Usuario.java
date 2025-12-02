package clinica.com.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório")
	@Column(nullable = false)
	private String nome;
	
	@NotBlank(message = "E-mail é obrigatório")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotBlank(message = "Senha é obrigatório")
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "TB_USUARIO_ROLE",
	        joinColumns = @JoinColumn(name = "usuario_id"),
 	        inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private List<Role> roles;
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}