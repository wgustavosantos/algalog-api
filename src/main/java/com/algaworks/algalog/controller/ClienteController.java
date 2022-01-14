package com.algaworks.algalog.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController /* Componente capaz de tratar requisições */
public class ClienteController {
	
	@GetMapping("/clientes") /* Mapeamento da requisição */
	public List<Cliente> listar() {
		
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Gustavo");
		cliente1.setEmail("Gustavo@gmail.com");
		cliente1.setTelefone("91993720104");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Maria");
		cliente2.setEmail("Maria@gmail.com");
		cliente2.setTelefone("9197845785");
		
		return Arrays.asList(cliente1, cliente2);
	}

}
