package com.algaworks.algalog.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice /* Componte do Spring mas com prositos especificos de tratar excecao para globalmente, p/ todos os controladores */
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro.Campo> campos = new ArrayList<>();
		
		for( ObjectError error : ex.getBindingResult().getAllErrors()) {
			
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale()); /* String mensagem = error.getDefaultMessage(); */
			
			Erro.Campo erro = new Erro.Campo(nome, mensagem);
			campos.add(erro);
			
		}
		
		Erro erro = new Erro();
		erro.setStatus(status.value());
		erro.setDataHora(LocalDateTime.now());
		erro.setTitulo("Um ou mais campos estao invalidos. tente novamente");
		erro.setCampos(campos);
		return handleExceptionInternal(ex, erro , headers, status, request);
	}
	
}