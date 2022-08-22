package com.teste.demo.Dtos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


import com.fasterxml.jackson.annotation.JsonFormat;
/*@Table(uniqueConstraints=
@UniqueConstraint(columnNames={"nomePaciente", "dataConsulta"}))*/
public class CadastroDto implements Serializable {
	
	private static final long serialVersionUID = -8105241933692707649L;
	
	private Long id;
	
	private String nomePaciente;

	private String crmMedico;

	private String nomeMedico;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataConsulta;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private Date horaConsulta;

	private int salaConsulta;
	
	public CadastroDto() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull (message = "Nome do paciente é uma informação obrigatória")
	@Length (min = 3, max = 40, message = "Nome do Paciente deve estar entre 3 e 40 caracteres")
//	@Column (unique = true)
	public String getNomePaciente() {
		return nomePaciente;
	}
	
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	@NotNull(message = "CRM Médico é uma informação obrigatória")
	@Length (min = 13, max = 13, message = "CRM do médico informado nos moldes errado. Correto: CRM/SP xxxxxx")
	public String getCrmMedico() {
		return crmMedico;
	}

	public void setCrmMedico(String crmMedico) {
		this.crmMedico = crmMedico;
	}
	@NotNull(message = "Nome do médico é uma informação obrigatória")
	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}
	@NotNull(message = "Data da consulta é uma informação obrigatória, no formato yyyy-MM-dd" )
//	@Column (unique = true)
	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	@NotNull(message = "Hora da consulta é uma informação obrigatória" )
	public Date getHoraConsulta() {
		return horaConsulta;
	}

	public void setHoraConsulta(Date horaConsulta) {
		this.horaConsulta = horaConsulta;
	}
	@NotNull(message = "Sala da consulta é uma informação obrigatória")
	public int getSalaConsulta() {
		return salaConsulta;
	}

	public void setSalaConsulta(int salaConsulta) {
		this.salaConsulta = salaConsulta;
	}
	
	@Override
	public String toString() {
		return "Cadastro [id = " + id + ", Nome Paciente = " + nomePaciente + "Nome Médico = " + nomeMedico + 
				"Data Consulta = " + dataConsulta + "Hora Consulta" + horaConsulta + "Sala Consulta" +salaConsulta + "]";
	}

}
