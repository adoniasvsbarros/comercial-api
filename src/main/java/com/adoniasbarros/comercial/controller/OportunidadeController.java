package com.adoniasbarros.comercial.controller;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.adoniasbarros.comercial.model.Oportunidade;
import com.adoniasbarros.comercial.repository.OportunidadeRepository;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/oportunidades")
public class OportunidadeController {

	private final OportunidadeRepository oportunidadeRepository;
	
	public OportunidadeController(OportunidadeRepository oportunidadeRepository) {
		this.oportunidadeRepository = oportunidadeRepository;
	}
	
	@GetMapping
	public List<Oportunidade> lista() {
			
		return oportunidadeRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Oportunidade> obterOportunidades(@PathVariable Long id) {
		
		Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
		
		if(oportunidade.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oportunidade.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade adicionarOportunidade(@Valid @RequestBody Oportunidade oportunidade) {
		Optional<Oportunidade> oportunidadeExistente = oportunidadeRepository
				.findByDescricaoAndNomeProspector(oportunidade.getDescricao(), oportunidade.getNomeProspector());
		
		if(oportunidadeExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					"Já existe uma oportunidade com o mesmo prospecto e descricao");
		}
		
		return oportunidadeRepository.save(oportunidade);
	}
	
	@DeleteMapping("{id}")
	public void deletarOportunidade(@PathVariable Long id) {
		oportunidadeRepository.deleteById(id);
	}
	
	
}
