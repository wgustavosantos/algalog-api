package com.algaworks.algalog.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL) /* Incluir no corpo da requisicao apenas propriedades que nao estao nulas */
@Getter
@Setter
public class Erro {
	
	private Integer status;
	private LocalDateTime dataHora;
	private String titulo;
	
	private List<Campo> campos;
	
	/* Inner class */
	@Getter
	@AllArgsConstructor
	public static class Campo{
		
		private String nome;
		private String mensagem;
		
	}
	

}
