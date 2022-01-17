package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Destinatario {
	
	@NotBlank
	@Column(name = "destinatario_nome")
	private String nome;
	
	@NotBlank
	@Column(name = "destinatario_logradouro")
	private String logradouro;
	
	@Column(name = "destinatario_numero")
	@NotBlank
	private String numero;
	
	@Column(name = "destinatario_complemento")
	@NotBlank
	private String complemento;	
	
	@NotBlank
	@Column(name = "destinatario_bairro")
	private String bairro;

}
