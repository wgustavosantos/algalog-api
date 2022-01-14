package com.algaworks.algalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

/*@AllArgsConstructor  Lombok usando o construtor de todas as propriedades da classe, no caso é somente clienteRepository  */
@RestController /* Componente capaz de tratar requisições */
public class ClienteController {
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	/* public ClienteController(ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
	} Alternativa sem a notacao @Autowired*/

	@GetMapping("/clientes") /* Mapeamento da requisição */
	public List<Cliente> listar() {
		
		return clienteRepository.findAll();
		//return clienteRepository.findByNome("Gustavo");
		//return clienteRepository.findByNomeContaining("a");
	}

}
