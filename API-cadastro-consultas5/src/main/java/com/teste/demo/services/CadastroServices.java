package com.teste.demo.services;

import java.io.IOException;

import java.util.Date;

import java.util.List;
/*import java.util.Optional;*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.teste.demo.Dtos.CadastroDto;
import com.teste.demo.Dtos.CadastroDtoResponse;
import com.teste.demo.entity.Cadastro;
import com.teste.demo.repository.CadastroRepository;

/*import com.teste.demo.services.exceptions.NomeDataException;*/
/*import com.teste.demo.services.exceptions.CadastroServiceException;*/
/*import com.teste.demo.services.exceptions.CadastroServiceException;*/
import com.teste.demo.utils.conversor.Conversor;

/*import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;*/

@Service
public class CadastroServices {
	
	@Autowired
	private  CadastroRepository cadastroRepository;
	
	public List<Cadastro> listarCadastros() {
		return cadastroRepository.findAll();
	}
	
	public Cadastro salvar (CadastroDto cadastroDto) throws DataIntegrityViolationException {
		Cadastro cadastro = new Cadastro();
		
		cadastro.setNomePaciente(cadastroDto.getNomePaciente());
		cadastro.setCrmMedico(cadastroDto.getCrmMedico());
		cadastro.setNomeMedico(cadastroDto.getNomeMedico());
		cadastro.setDataConsulta(cadastroDto.getDataConsulta());
		cadastro.setHoraConsulta(cadastroDto.getHoraConsulta());
		cadastro.setSalaConsulta(cadastroDto.getSalaConsulta());
				
		try { cadastroRepository.save(cadastro);
		} catch(DataIntegrityViolationException e){
			throw new DataIntegrityViolationException("Consulta já agendada no dia para esse paciente");
        }
		return cadastro;
		
	}

	public CadastroDtoResponse buscar(Long id)throws IOException, NotFoundException {
		Cadastro cadastro = cadastroRepository.findById(id).orElse(null);
		
		if (cadastro == null) {
			throw new NotFoundException();
		}
		CadastroDtoResponse cadastroDtoResponse = Conversor.converterCadastrotoCadastroDtoResponse(cadastro);
		return cadastroDtoResponse;
		
	}
	
	/*public CadastroDtoResponse buscarPorData(Date dataConsulta)throws IOException, NotFoundException {
		Cadastro cadastro = cadastroRepository.findBydataConsulta(dataConsulta);
		
		if (cadastro == null) {
			throw new NotFoundException();
		}
		CadastroDtoResponse cadastroDtoResponse = Conversor.converterCadastrotoCadastroDtoResponse(cadastro);
		return cadastroDtoResponse;
		
	}*/
	
	public List<Cadastro> Delete(Cadastro cadastro) {
		cadastroRepository.delete(cadastro);
		
		return cadastroRepository.findAll();
	}
	
	public Cadastro buscarSemTratativa(Long id) {
		Cadastro cadastro = cadastroRepository.findById(id).orElse(null);
		return cadastro;
	}
	
	public Cadastro update (CadastroDto cadastroDto, Long id) {
		Cadastro cadastroExistente = cadastroRepository.findById(id).orElse(null);
		
		cadastroExistente.setNomePaciente(cadastroDto.getNomePaciente());
		cadastroExistente.setCrmMedico(cadastroDto.getCrmMedico());
		cadastroExistente.setNomeMedico(cadastroDto.getNomeMedico());
		cadastroExistente.setDataConsulta(cadastroDto.getDataConsulta());
		cadastroExistente.setHoraConsulta(cadastroDto.getHoraConsulta());
		cadastroExistente.setSalaConsulta(cadastroDto.getSalaConsulta());
		return cadastroRepository.save(cadastroExistente);
	}
	
	
}
