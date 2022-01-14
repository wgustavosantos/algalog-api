package com.algaworks.algalog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

/*@AllArgsConstructor  Lombok usando o construtor de todas as propriedades da classe, no caso é somente clienteRepository  */
@RestController /* Componente capaz de tratar requisições */
@RequestMapping("/clientes") /* Mapeamento a nvel de classe, do verbo get para /clientes */
public class ClienteController {
	
	@Autowired
	private CatalogoClienteService catalogoClienteService;
	
	/* public ClienteController(ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
	} Alternativa sem a notacao @Autowired*/

	@GetMapping /* Com requestMapping só basta usar a notação "getMapping" para usar o mapeamento da classe*/
	public List<Cliente> listar() {
		
		return catalogoClienteService.findAll();
		//return clienteRepository.findByNome("Gustavo");
		//return clienteRepository.findByNomeContaining("a");
	}
	
	@GetMapping("{clienteId}") /* Antes -> @GetMapping("/clientes/{clienteId}") gracas ao @RequestMapping */
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return catalogoClienteService.findById(clienteId);
		
//		Optional<Cliente> clientes = clienteRepository.findById(clienteId);
//		
//		if(clientes.isPresent()) {
//			return ResponseEntity.ok(clientes.get());
//		}
//		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) /* Em vez de retornar o 200 ok retorna 201 created */
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) { /* Vincular o parâmetro do método ao corpo da requisicao, deserializar o json que veio no corpo da requisicao*/
		
		return catalogoClienteService.salvar(cliente); // boa prática retornar o bjeto criado
	}
	
	@PutMapping("{clienteId}")
	public ResponseEntity<Cliente> atualizar (@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente, CrudRepository<Cliente, Long> clienteRepository){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = catalogoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
		
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover (@PathVariable Long clienteId){
		
		if(catalogoClienteService.ValidacaoId(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		catalogoClienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
		
	}

}
