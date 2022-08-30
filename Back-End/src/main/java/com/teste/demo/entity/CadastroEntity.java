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

	public CadastroEntity() {

	}

	public CadastroEntity(Long id, String nomePaciente, String crmMedico, String nomeMedico, Date dataConsulta,
			int salaConsulta) {
		this.id = id;
		this.nomePaciente = nomePaciente;
		this.crmMedico = crmMedico;
		this.nomeMedico = nomeMedico;
		this.dataConsulta = dataConsulta;
		this.salaConsulta = salaConsulta;

	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	public Date getHoraConsulta() {
		return horaConsulta;
	}

	public void setHoraConsulta(Date horaConsulta) {
		this.horaConsulta = horaConsulta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = true)
	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getCrmMedico() {
		return crmMedico;
	}

	public void setCrmMedico(String crmMedico) {
		this.crmMedico = crmMedico;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public int getSalaConsulta() {
		return salaConsulta;
	}

	public void setSalaConsulta(int salaConsulta) {
		this.salaConsulta = salaConsulta;
	}

	@Override
	public String toString() {
		return "Cadastro [id = " + id + ", Nome Paciente = " + nomePaciente + "Nome Médico = " + nomeMedico
				+ "Data Consulta = " + dataConsulta + "Hora Consulta" + horaConsulta + "Sala Consulta = " + salaConsulta
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(crmMedico, dataConsulta, horaConsulta, id, nomeMedico, nomePaciente, salaConsulta);
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
		return Objects.equals(crmMedico, other.crmMedico) && Objects.equals(dataConsulta, other.dataConsulta)
				&& Objects.equals(horaConsulta, other.horaConsulta) && Objects.equals(id, other.id)
				&& Objects.equals(nomeMedico, other.nomeMedico) && Objects.equals(nomePaciente, other.nomePaciente)
				&& salaConsulta == other.salaConsulta;
	}

	public static void sort(Comparator<CadastroEntity> comparing) {

	}

}
