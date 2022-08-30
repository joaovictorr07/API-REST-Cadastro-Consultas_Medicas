package com.teste.demo.entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Component
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "nomePaciente", "dataConsulta" }))
public class CadastroEntity implements Serializable {

	private static final long serialVersionUID = -6888542263201514002L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nomePaciente;

	private String crmMedico;

	private String nomeMedico;

	private Date dataConsulta;

	private Date horaConsulta;

	private int salaConsulta;

	public CadastroEntity(Long id, String nomePaciente, String crmMedico, String nomeMedico, Date dataConsulta, int salaConsulta) {
		this.id = id;
		this.nomePaciente = nomePaciente;
		this.crmMedico = crmMedico;
		this.nomeMedico = nomeMedico;
		this.dataConsulta = dataConsulta;
		this.salaConsulta = salaConsulta;

	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getDataConsulta() {
		return this.dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	public Date getHoraConsulta() {
		return this.horaConsulta;
	}

	public void setHoraConsulta(Date horaConsulta) {
		this.horaConsulta = horaConsulta;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = true)
	public String getNomePaciente() {
		return this.nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getCrmMedico() {
		return this.crmMedico;
	}

	public void setCrmMedico(String crmMedico) {
		this.crmMedico = crmMedico;
	}

	public String getNomeMedico() {
		return this.nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public int getSalaConsulta() {
		return this.salaConsulta;
	}

	public void setSalaConsulta(int salaConsulta) {
		this.salaConsulta = salaConsulta;
	}

	@Override
	public String toString() {
		return "Cadastro [id = " + this.id + ", Nome Paciente = " + this.nomePaciente + "Nome Médico = " + this.nomeMedico + "Data Consulta = "
				+ this.dataConsulta + "Hora Consulta" + this.horaConsulta + "Sala Consulta = " + this.salaConsulta + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.crmMedico, this.dataConsulta, this.horaConsulta, this.id, this.nomeMedico, this.nomePaciente, this.salaConsulta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CadastroEntity other = (CadastroEntity) obj;
		return Objects.equals(this.crmMedico, other.crmMedico) && Objects.equals(this.dataConsulta, other.dataConsulta)
				&& Objects.equals(this.horaConsulta, other.horaConsulta) && Objects.equals(this.id, other.id)
				&& Objects.equals(this.nomeMedico, other.nomeMedico) && Objects.equals(this.nomePaciente, other.nomePaciente)
				&& this.salaConsulta == other.salaConsulta;
	}

	/**
	 * @param comparator 
	 * @param comparing  
	 */
	public static void sort(Comparator<CadastroEntity> comparing) {
		CadastroEntity.sort(Comparator.comparing(CadastroEntity::getDataConsulta));
 }

}
