package com.teste.demo.controller;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
/*import org.springframework.web.bind.annotation.ExceptionHandler;*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/*import org.springframework.web.bind.annotation.PathVariable;*/
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
import com.teste.demo.entity.Cadastro;
/*import com.teste.demo.repositorios.CadastroRepositorio;*/
import com.teste.demo.responses.Responsivo;
import com.teste.demo.services.CadastroServices;
/*import com.teste.demo.services.exceptions.NomeDataException;*/




@RestController
@RequestMapping("/cadastro")
public class CadastroController {
	
	@Autowired
	private  CadastroServices cadastroService;
	
	@PostMapping( produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<Responsivo<Cadastro>> cadastrar(@Validated @RequestBody CadastroDto cadastroDto, BindingResult result){
		Responsivo<Cadastro> response = new Responsivo<Cadastro>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Cadastro cadastroSalvo = this.cadastroService.salvar(cadastroDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cadastroDto.getId()).toUri();
		response.setData(cadastroSalvo);
		return ResponseEntity.created(location).body(response);
	}
	
	
	@GetMapping
	public ResponseEntity<List<Cadastro>> listar() {
		List<Cadastro> cadastros = cadastroService.listarCadastros();
		cadastros.sort(Comparator.comparing(Cadastro::getDataConsulta));
//		cadastros.sort(Comparator.comparing(Cadastro::getHoraConsulta));
		return ResponseEntity.status(HttpStatus.OK).body(cadastros);
	}
	
	@GetMapping(path = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Responsivo<CadastroDtoResponse>> buscar(@PathVariable("id") Long id) throws IOException, NotFoundException {
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
	
	/*@GetMapping(path = "/data/{dataConsulta}")
	public ResponseEntity<Responsivo<CadastroDtoResponse>> buscarPorData(@PathVariable("dataConsulta") String dataConsultaString) throws IOException, NotFoundException, ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date dataConsulta = formato.parse(dataConsultaString);
		
		Responsivo<CadastroDtoResponse> response = new Responsivo<CadastroDtoResponse>();
		CadastroDtoResponse cadastroDtoResponse;
		
		try {
		cadastroDtoResponse = cadastroService.buscarPorData(dataConsulta);
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
	}*/
	
	@DeleteMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete (@PathVariable("id") Long id) {
		Cadastro cadastro = cadastroService.buscarSemTratativa(id);
		cadastroService.Delete(cadastro);
	}
	
	@PutMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Responsivo<Cadastro>> alterar(@PathVariable("id") Long id,@Valid @RequestBody CadastroDto cadastroDto){
		cadastroService.update(cadastroDto, id);
		Responsivo<Cadastro> response = new Responsivo<>();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}
	

	

}
