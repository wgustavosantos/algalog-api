package com.algaworks.algalog.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

@Service /* Torna um componente do Spring porém, com uma semantica de um serviço */
public class CatalogoClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow( () -> new NegocioException("Cliente não encontrado"));
	}
	
	@Transactional /* Declara que  metodo deve ser executado dentro de uma transacao. Caso aconteca um erro, as alteracoes no bd devem ser descartadas */
	public Cliente salvar(Cliente cliente) {
		
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
			.stream()
			.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailEmUso) {
			throw new NegocioException("Já existe cliente cadastrado com esse e-mail.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir (Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

	@Transactional
	public List<Cliente> findAll() {
		
		return clienteRepository.findAll();
	}
	
	public ResponseEntity<Cliente> findById(Long clienteId){
		
		return clienteRepository.findById(clienteId)
				//.map(cliente -> ResponseEntity.ok(cliente))
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	public ResponseEntity<Cliente> atualizar(Cliente cliente){
		
		return ResponseEntity.ok(cliente);
	}
	
	public Boolean ValidacaoId(Long clienteId) {
		
		if(!clienteRepository.existsById(clienteId)) {
			return true;
		}else {
			return false;
		}
		
		
	}

}
