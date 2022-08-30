package com.teste.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.teste.demo.dtos.CadastroDto;
import com.teste.demo.dtos.CadastroDtoResponse;
import com.teste.demo.entity.CadastroEntity;
import com.teste.demo.repository.CadastroRepository;
import com.teste.demo.utils.conversor.Conversor;

@Service
public class CadastroServices {

	@Autowired
	private CadastroRepository cadastroRepository;

	public List<CadastroEntity> listarCadastros() {
		return this.cadastroRepository.findAll();
	}

	public CadastroEntity salvar(CadastroDto cadastroDto) throws DataIntegrityViolationException {
		CadastroEntity cadastroEntity = new CadastroEntity(null, null, null, null, null, 0);

		cadastroEntity.setNomePaciente(cadastroDto.getNomePaciente());
		cadastroEntity.setCrmMedico(cadastroDto.getCrmMedico());
		cadastroEntity.setNomeMedico(cadastroDto.getNomeMedico());
		cadastroEntity.setDataConsulta(cadastroDto.getDataConsulta());
		cadastroEntity.setHoraConsulta(cadastroDto.getHoraConsulta());
		cadastroEntity.setSalaConsulta(cadastroDto.getSalaConsulta());

		try {
			this.cadastroRepository.save(cadastroEntity);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Consulta já agendada no dia para esse paciente");
		}
		return cadastroEntity;

	}

	public CadastroDtoResponse buscar(Long id) throws NotFoundException {
		CadastroEntity cadastroEntity = this.cadastroRepository.findById(id).orElse(null);

		if (cadastroEntity == null) {
			throw new NotFoundException();
		}
		return Conversor.converterCadastrotoCadastroDtoResponse(cadastroEntity);

	}

	public List<CadastroEntity> delete(CadastroEntity cadastroEntity) {
		this.cadastroRepository.delete(cadastroEntity);

		return this.cadastroRepository.findAll();
	}

	public CadastroEntity buscarSemTratativa(Long id) {
		return this.cadastroRepository.findById(id).orElse(null);
	}

	public CadastroEntity update(CadastroDto cadastroDto, Long id) throws NotFoundException {
		CadastroEntity cadastroExistente = this.cadastroRepository.findById(id).orElse(null);

		if (cadastroExistente == null) {
			throw new NotFoundException();
		} else {
			cadastroExistente.setNomePaciente(cadastroDto.getNomePaciente());
			cadastroExistente.setCrmMedico(cadastroDto.getCrmMedico());
			cadastroExistente.setNomeMedico(cadastroDto.getNomeMedico());
			cadastroExistente.setDataConsulta(cadastroDto.getDataConsulta());
			cadastroExistente.setHoraConsulta(cadastroDto.getHoraConsulta());
			cadastroExistente.setSalaConsulta(cadastroDto.getSalaConsulta());
		}
		return this.cadastroRepository.save(cadastroExistente);
	}

}
