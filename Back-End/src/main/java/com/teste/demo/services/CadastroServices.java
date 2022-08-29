package com.teste.demo.services;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.teste.demo.Dtos.CadastroDto;
import com.teste.demo.Dtos.CadastroDtoResponse;
import com.teste.demo.entity.CadastroEntity;
import com.teste.demo.repository.CadastroRepository;
import com.teste.demo.utils.conversor.Conversor;


@Service
public class CadastroServices {
	
	@Autowired
	private  CadastroRepository cadastroRepository;
	
	public List<CadastroEntity> listarCadastros() {
		return cadastroRepository.findAll();
	}
	
	public CadastroEntity salvar (CadastroDto cadastroDto) throws DataIntegrityViolationException {
		CadastroEntity cadastroEntity = new CadastroEntity();
		
		cadastroEntity.setNomePaciente(cadastroDto.getNomePaciente());
		cadastroEntity.setCrmMedico(cadastroDto.getCrmMedico());
		cadastroEntity.setNomeMedico(cadastroDto.getNomeMedico());
		cadastroEntity.setDataConsulta(cadastroDto.getDataConsulta());
		cadastroEntity.setHoraConsulta(cadastroDto.getHoraConsulta());
		cadastroEntity.setSalaConsulta(cadastroDto.getSalaConsulta());
				
		try { cadastroRepository.save(cadastroEntity);
		} catch(DataIntegrityViolationException e){
			throw new DataIntegrityViolationException("Consulta já agendada no dia para esse paciente");
        }
		return cadastroEntity;
		
	}

	public CadastroDtoResponse buscar(Long id)throws IOException, NotFoundException {
		CadastroEntity cadastroEntity = cadastroRepository.findById(id).orElse(null);
		
		if (cadastroEntity == null) {
			throw new NotFoundException();
		}
		CadastroDtoResponse cadastroDtoResponse = Conversor.converterCadastrotoCadastroDtoResponse(cadastroEntity);
		return cadastroDtoResponse;
		
	}
	
	public List<CadastroEntity> Delete(CadastroEntity cadastroEntity) {
		cadastroRepository.delete(cadastroEntity);
		
		return cadastroRepository.findAll();
	}
	
	public CadastroEntity buscarSemTratativa(Long id) {
		CadastroEntity cadastroEntity = cadastroRepository.findById(id).orElse(null);
		return cadastroEntity;
	}
	
	public CadastroEntity update (CadastroDto cadastroDto, Long id) {
		CadastroEntity cadastroExistente = cadastroRepository.findById(id).orElse(null);
		
		cadastroExistente.setNomePaciente(cadastroDto.getNomePaciente());
		cadastroExistente.setCrmMedico(cadastroDto.getCrmMedico());
		cadastroExistente.setNomeMedico(cadastroDto.getNomeMedico());
		cadastroExistente.setDataConsulta(cadastroDto.getDataConsulta());
		cadastroExistente.setHoraConsulta(cadastroDto.getHoraConsulta());
		cadastroExistente.setSalaConsulta(cadastroDto.getSalaConsulta());
		return cadastroRepository.save(cadastroExistente);
	}
	
	
}
