package clinica.com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PRONTUARIO")
public class Prontuário {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroProntuario;
    private String alergias;
    private String doencasPreexistentes;
    private String medicamentosEmUso;
    private String historicoClinico;

    @OneToOne
    @JoinColumn(name = "paciente_id", nullable = true)
    private Paciente paciente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroProntuario() {
		return numeroProntuario;
	}

	public void setNumeroProntuario(String numeroProntuario) {
		this.numeroProntuario = numeroProntuario;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getDoencasPreexistentes() {
		return doencasPreexistentes;
	}

	public void setDoencasPreexistentes(String doencasPreexistentes) {
		this.doencasPreexistentes = doencasPreexistentes;
	}

	public String getMedicamentosEmUso() {
		return medicamentosEmUso;
	}

	public void setMedicamentosEmUso(String medicamentosEmUso) {
		this.medicamentosEmUso = medicamentosEmUso;
	}

	public String getHistoricoClinico() {
		return historicoClinico;
	}

	public void setHistoricoClinico(String historicoClinico) {
		this.historicoClinico = historicoClinico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
