package com.teste.demo.utils.conversor;

import com.teste.demo.Dtos.CadastroDtoResponse;
import com.teste.demo.entity.Cadastro;

public abstract class Conversor {
	
	public static CadastroDtoResponse converterCadastrotoCadastroDtoResponse (Cadastro cadastro) {
		CadastroDtoResponse cadastroDtoResponse = new CadastroDtoResponse();
		cadastroDtoResponse.setId(cadastro.getId());
		cadastroDtoResponse.setNomePaciente(cadastro.getNomePaciente());
		cadastroDtoResponse.setCrmMedico(cadastro.getCrmMedico());
		cadastroDtoResponse.setNomeMedico(cadastro.getNomeMedico());
		cadastroDtoResponse.setDataConsulta(cadastro.getDataConsulta());
		cadastroDtoResponse.setSalaConsulta(cadastro.getSalaConsulta());
		
		return cadastroDtoResponse;
	}

}
