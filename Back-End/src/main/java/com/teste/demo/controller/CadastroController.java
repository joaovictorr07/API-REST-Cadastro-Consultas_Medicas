package com.teste.demo.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.teste.demo.Dtos.CadastroDto;
import com.teste.demo.Dtos.CadastroDtoResponse;
import com.teste.demo.entity.CadastroEntity;
import com.teste.demo.responses.Responsivo;
import com.teste.demo.services.CadastroServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cadastro")
@Api("Gerenciador de consultas médicas controller")
public class CadastroController {

	@Autowired
	private CadastroServices cadastroService;

	@ApiOperation(value = "Cadastra uma consulta médica")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<Responsivo<CadastroEntity>> cadastrar(@Validated @RequestBody CadastroDto cadastroDto,
			BindingResult result) {
		Responsivo<CadastroEntity> response = new Responsivo<CadastroEntity>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		CadastroEntity cadastroSalvo = this.cadastroService.salvar(cadastroDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cadastroDto.getId()).toUri();
		response.setData(cadastroSalvo);
		return ResponseEntity.created(location).body(response);
	}

	@ApiOperation(value = "Retorna todas as consultas médicas")
	@GetMapping
	public ResponseEntity<List<CadastroEntity>> listar() {
		List<CadastroEntity> cadastroEntities = cadastroService.listarCadastros();
		cadastroEntities.sort(Comparator.comparing(CadastroEntity::getDataConsulta));
		return ResponseEntity.status(HttpStatus.OK).body(cadastroEntities);
	}

	@ApiOperation(value = "Retorna uma consulta médica específica")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Responsivo<CadastroDtoResponse>> buscar(@PathVariable("id") Long id)
			throws IOException, NotFoundException {
		Responsivo<CadastroDtoResponse> response = new Responsivo<CadastroDtoResponse>();
		CadastroDtoResponse cadastroDtoResponse;

		try {
			cadastroDtoResponse = cadastroService.buscar(id);
		}

		catch (NotFoundException e) {
			response.setErrors(Collections.singletonList(e.getMessage()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		catch (HttpClientErrorException hce) {
			response.setErrors(Collections.singletonList(hce.getStatusText()));
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
		}
		response.setData(cadastroDtoResponse);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@ApiOperation(value = "Deleta uma consulta médica específica")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("id") Long id) {
		CadastroEntity cadastroEntity = cadastroService.buscarSemTratativa(id);
		cadastroService.Delete(cadastroEntity);
	}

	@ApiOperation(value = "Atualiza uma consulta médica específica")
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Responsivo<CadastroEntity>> update(@PathVariable("id") Long id,
			@Valid @RequestBody CadastroDto cadastroDto) {
		cadastroService.update(cadastroDto, id);
		Responsivo<CadastroEntity> response = new Responsivo<>();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}

}
