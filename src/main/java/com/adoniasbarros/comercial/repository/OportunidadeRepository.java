package com.adoniasbarros.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adoniasbarros.comercial.model.Oportunidade;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long>{

	Optional<Oportunidade> findByDescricaoAndNomeProspector(String descricao, String nomeProspector);
}
