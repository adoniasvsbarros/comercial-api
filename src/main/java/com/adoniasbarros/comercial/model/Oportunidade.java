package com.adoniasbarros.comercial.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"nomeProspector", "descricao", "valor"})
@Entity
public class Oportunidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(max = 80)
	@Column(name = "nome_prospecto")
	private String nomeProspector;
	
	@NotEmpty
	@Size(max = 200)
	private String descricao;

	@Min(0)
	private BigDecimal valor;
}
